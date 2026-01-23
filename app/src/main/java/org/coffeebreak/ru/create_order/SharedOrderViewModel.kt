package org.coffeebreak.ru.create_order

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.model.FullOrderModel
import org.coffeebreak.domain.usecase.coffee.GetCoffeeByIdUseCase
import org.coffeebreak.domain.usecase.order.OrderUseCase
import org.coffeebreak.domain.usecase.order.SetOrderUseCase
import org.coffeebreak.domain.usecase.order.SetPreOrderUseCase
import org.coffeebreak.ru.Route
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.abs


@HiltViewModel
class SharedOrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase,
    private val setPreOrderUseCase: SetPreOrderUseCase,
    private val setOrderUseCase: SetOrderUseCase,
    private val orderUseCase: OrderUseCase
) : ViewModel() {
    private val _createOrderState = mutableStateOf(CreateOrderState())
    val createOrderState: State<CreateOrderState> = _createOrderState

    private val _consState = mutableStateOf(CoffeeConstructorState())
    val consState: State<CoffeeConstructorState> = _consState

    private val _coffeeId = MutableStateFlow<String?>(null)

    val coffeeId = _coffeeId.onStart {

        val tt = savedStateHandle.toRoute<Route.CreateOrder>().id
        _coffeeId.update { tt }

        Log.e("TAG123", "$tt")
        val id = if (tt.isNullOrBlank()) {
            throw NullPointerException("Айди пустой")
        } else {
            tt
        }
        val res = getCoffeeByIdUseCase.execute(
            id
        )
        if (res.isSuccess) {
            withContext(Dispatchers.Main) {
                _createOrderState.value = _createOrderState.value.copy(
                    coffee = res.getOrNull()!!,
                    isLoading = false
                )
            }
        } else {
            withContext(Dispatchers.Main) {
                _createOrderState.value = _createOrderState.value.copy(
                    isError = true,
                    errorMessage = res.exceptionOrNull()!!.message!!
                )
            }
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)


//    val tt = savedStateHandle
//        .get<String>("id")


    init {
//        val tt = savedStateHandle.toRoute<Route.CreateOrder>()
//            .id
//
//        _coffeeId.update { tt }
//
//        viewModelScope.launch(Dispatchers.IO) {
//            if (_createOrderState.value.coffeeId == "") {
//                withContext(Dispatchers.Main) {
//                    _createOrderState.value = _createOrderState.value.copy (
//                        coffeeId = tt!!
//                    )
//                }
//            }
//        }

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 15)
        _createOrderState.value = _createOrderState.value.copy(
            timeHours = calendar.get(Calendar.HOUR_OF_DAY).toString(),
            timeMinutes =
                if (calendar.get(Calendar.MINUTE) >= 10) {
                    calendar.get(Calendar.MINUTE).toString()
                } else {
                    "0${calendar.get(Calendar.MINUTE)}"
                }

        )


    }

    fun onCreateEvent(event: CreateOrderEvents) {
        when (event) {
            CreateOrderEvents.OnRisChange -> {
                _createOrderState.value = _createOrderState.value.copy(
                    ristrettoOne = !_createOrderState.value.ristrettoOne
                )
            }

            CreateOrderEvents.OnPickupChange -> {
                _createOrderState.value = _createOrderState.value.copy(
                    pickupPlace = abs(_createOrderState.value.pickupPlace - 1)
                )
            }

            CreateOrderEvents.OnAddClick -> {
                _createOrderState.value = _createOrderState.value.copy(
                    count = _createOrderState.value.count + 1
                )
            }

            CreateOrderEvents.OnDelClick -> {
                if (_createOrderState.value.count > 1) {
                    _createOrderState.value = _createOrderState.value.copy(
                        count = _createOrderState.value.count - 1
                    )
                }
            }

            CreateOrderEvents.OnSmallClick -> {
                _createOrderState.value = _createOrderState.value.copy(
                    volume = 0
                )
            }

            CreateOrderEvents.OnMediumClick -> {
                _createOrderState.value = _createOrderState.value.copy(
                    volume = 1
                )
            }

            CreateOrderEvents.OnLargeClick -> {
                _createOrderState.value = _createOrderState.value.copy(
                    volume = 2
                )
            }

            CreateOrderEvents.OnTimeSwitch -> {
                _createOrderState.value = _createOrderState.value.copy(
                    isSpecificTime = !_createOrderState.value.isSpecificTime
                )
            }

            CreateOrderEvents.OnPickerClick -> {
                _createOrderState.value = _createOrderState.value.copy(
                    isTimeInput = true
                )
            }

            is CreateOrderEvents.OnTimeChange -> {
                _createOrderState.value = _createOrderState.value.copy(
                    timeHours = event.h.toString(),
                    timeMinutes = event.m.toString()
                )
            }

            CreateOrderEvents.OnConstructorClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = setPreOrderUseCase.execute(
                        FullOrderModel(
                            coffeeId = _coffeeId.value!! /*_createOrderState.value.coffeeId*/,
                            count = _createOrderState.value.count,
                            ristretto = orderUseCase.parseRistretto(_createOrderState.value.ristrettoOne),
                            place = orderUseCase.parsePickupPlace(_createOrderState.value.pickupPlace),
                            volume = orderUseCase.parseVolume(_createOrderState.value.volume),
                            specTime = _createOrderState.value.isSpecificTime,
                            time = "${_createOrderState.value.timeHours}:${_createOrderState.value.timeMinutes}:00",
                            totalCoast = _createOrderState.value.totalCoast.toLong()
                        )
                    )
                    if (res.isValid) {
                        _createOrderState.value = _createOrderState.value.copy(
                            isSuccess = true
                        )
                    } else {
                        _createOrderState.value = _createOrderState.value.copy(
                            isError = true,
                            errorMessage = res.errorMessage
                        )
                    }
                }
            }

            CreateOrderEvents.OnNextClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = setOrderUseCase.execute(
                        FullOrderModel(
                            coffeeId = _coffeeId.value!! /*_createOrderState.value.coffeeId*/,
                            count = _createOrderState.value.count,
                            ristretto = orderUseCase.parseRistretto(_createOrderState.value.ristrettoOne),
                            place = orderUseCase.parsePickupPlace(_createOrderState.value.pickupPlace),
                            volume = orderUseCase.parseVolume(_createOrderState.value.volume),
                            specTime = _createOrderState.value.isSpecificTime,
                            time = "${_createOrderState.value.timeHours}:${_createOrderState.value.timeMinutes}:00",
                            totalCoast = _createOrderState.value.totalCoast.toLong()
                        )
                    )
                    if (res.isValid) {
                        _createOrderState.value = _createOrderState.value.copy(
                            isCreateSuccess = true
                        )
                    } else {
                        _createOrderState.value = _createOrderState.value.copy(
                            isError = true,
                            errorMessage = res.errorMessage
                        )
                    }
                }
            }
            CreateOrderEvents.OnCloseDialog -> {
                _createOrderState.value = _createOrderState.value.copy (
                    isError = false,
                    errorMessage = ""
                )
            }
        }
    }

    fun onConstructorEvent(event: CoffeeConstructorEvents) {
        when (event) {
            is CoffeeConstructorEvents.OnSliderChange -> {
                _consState.value = _consState.value.copy(
                    weight = event.weight
                )
            }

            CoffeeConstructorEvents.OnSmallClick -> {
                _consState.value = _consState.value.copy(
                    roasting = 1
                )
            }

            CoffeeConstructorEvents.OnMediumClick -> {
                _consState.value = _consState.value.copy(
                    roasting = 2
                )
            }

            CoffeeConstructorEvents.OnLargeClick -> {
                _consState.value = _consState.value.copy(
                    roasting = 3
                )
            }

            CoffeeConstructorEvents.OnSmallGrindingClick -> {
                _consState.value = _consState.value.copy(
                    grinding = 1
                )
            }

            CoffeeConstructorEvents.OnLargeGrindingClick -> {
                _consState.value = _consState.value.copy(
                    grinding = 2
                )
            }

            CoffeeConstructorEvents.OnIce0Click -> {
                _consState.value = _consState.value.copy(
                    ice = 0
                )
            }

            CoffeeConstructorEvents.OnSmallIceClick -> {
                _consState.value = _consState.value.copy(
                    ice = 1
                )
            }

            CoffeeConstructorEvents.OnMediumIceClick -> {
                _consState.value = _consState.value.copy(
                    ice = 2
                )
            }

            CoffeeConstructorEvents.OnLargeIceClick -> {
                _consState.value = _consState.value.copy(
                    ice = 3
                )
            }

            CoffeeConstructorEvents.OnDismissMenuClick -> {
                _consState.value = _consState.value.copy(
                    isMilkItems = false,
                    isSyrupItems = false
                )
            }

            CoffeeConstructorEvents.OnMilkItemsClick -> {
                _consState.value = _consState.value.copy(
                    isMilkItems = true
                )
            }

            CoffeeConstructorEvents.OnSyrupItemsClick -> {
                _consState.value = _consState.value.copy(
                    isSyrupItems = true
                )
            }

            is CoffeeConstructorEvents.OnSyrupItemClick -> {
                _consState.value = _consState.value.copy(
                    syrup = event.value,
                    isSyrupItems = false,
                )
            }

            is CoffeeConstructorEvents.OnMilkItemClick -> {
                _consState.value = _consState.value.copy(
                    milk = event.value,
                    isMilkItems = false
                )
            }

            CoffeeConstructorEvents.OnEncyclopediaClick -> {
                _consState.value = _consState.value.copy(
                    isDesc = true
                )

//                val a = TextClassificationHelper(context = con, listener = listener)
//                a.classify("Капучино")

//                viewModelScope.launch {
//                    runCatching {
//                        getCoffeeAIUseCase.execute(
//                            CoffeeAIModel(
//                                "Капучино"
//                            )
//                        )
//                    }.onSuccess {
//                        _state.value = _state.value.copy(
//                            desc = it,
//                            isDesc = true
//                        )
//                    }.onFailure {
//                        _state.value = _state.value.copy(
//                            isError = true,
//                            errorMessage = it.message!!,
//                        )
//                        Log.e("tensor", "${_state.value.errorMessage}: ");
//
//                    }
//                }
            }

            CoffeeConstructorEvents.OnCloseDialog -> {
                _consState.value = _consState.value.copy(
                    isError = false,
                    errorMessage = ""
                )
            }

            CoffeeConstructorEvents.OnCloseDesc -> {
                _consState.value = _consState.value.copy(
                    isDesc = false
                )
            }

            CoffeeConstructorEvents.OnNextClick -> {
                val baristaId = Route.Constructor
                    .baristaId
                val sortId = Route.Constructor
                    .sortId
                val supplementId = Route.Constructor
                    .supplementId

                viewModelScope.launch(Dispatchers.IO) {
                    val res = setOrderUseCase.execute(
                        FullOrderModel(
                            coffeeId = _coffeeId.value!!, /*_createOrderState.value.coffeeId*/
                            baristaId = baristaId,
                            sortId = sortId,
                            supplementId = supplementId,
                            count = _createOrderState.value.count,
                            ristretto = orderUseCase.parseRistretto(_createOrderState.value.ristrettoOne),
                            place = orderUseCase.parsePickupPlace(_createOrderState.value.pickupPlace),
                            volume = orderUseCase.parseVolume(_createOrderState.value.volume),
                            specTime = _createOrderState.value.isSpecificTime,
                            time = "${_createOrderState.value.timeHours}:${_createOrderState.value.timeMinutes}:00",
                            totalCoast = _createOrderState.value.totalCoast.toLong(),
                            grinding = _consState.value.grinding.toString(),
                            milk = _consState.value.milk,
                            syrup = _consState.value.syrup,
                            ice = _consState.value.ice.toString(),
                            roasting = _consState.value.roasting.toString(),
                        )
                    )
                    if (res.isValid) {
                        _consState.value = _consState.value.copy(
                            isSuccessCons = true
                        )
                    } else {
                        _consState.value = _consState.value.copy(
                            isError = true,
                            errorMessage = res.errorMessage
                        )
                    }
                }

                Route.Constructor.baristaId = null
                Route.Constructor.sortId = null
                Route.Constructor.supplementId = null
            }
        }
    }
}
