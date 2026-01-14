package org.coffeebreak.ru.create_order

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.coffee.GetCoffeeByIdUseCase
import org.coffeebreak.ru.Route
import java.util.Calendar
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase
) : ViewModel() {

    val t = savedStateHandle.toRoute<Route.CreateOrder>()
        .id
    private val _state = mutableStateOf(CreateOrderState())
    val state: State<CreateOrderState> = _state

    init {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, 15)
        _state.value = _state.value.copy(
            timeHours = calendar.get(Calendar.HOUR_OF_DAY),
            timeMinutes =
                if (calendar.get(Calendar.MINUTE) >= 10) {
                    calendar.get(Calendar.MINUTE)
                } else {
                    "0${calendar.get(Calendar.MINUTE)}".toInt()
                }

        )

        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeeByIdUseCase.execute(t!!)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        coffee = res.getOrNull()!!,
                        isLoading = false
                    )
                }
            } else {

            }
        }
    }

    fun loadCoffee(id: String) {

    }

    fun onEvent(event: CreateOrderEvents) {
        when (event) {
            CreateOrderEvents.OnRisChange -> {
                _state.value = _state.value.copy(
                    ristrettoOne = !_state.value.ristrettoOne
                )
            }

            CreateOrderEvents.OnPickupChange -> {
                _state.value = _state.value.copy(
                    pickupPlace = abs(_state.value.pickupPlace - 1)
                )
            }

            CreateOrderEvents.OnAddClick -> {
                _state.value = _state.value.copy(
                    count = _state.value.count + 1
                )
            }

            CreateOrderEvents.OnDelClick -> {
                if (_state.value.count > 1) {
                    _state.value = _state.value.copy(
                        count = _state.value.count - 1
                    )
                }
            }

            CreateOrderEvents.OnSmallClick -> {
                _state.value = _state.value.copy(
                    volume = 0
                )
            }

            CreateOrderEvents.OnMediumClick -> {
                _state.value = _state.value.copy(
                    volume = 1
                )
            }

            CreateOrderEvents.OnLargeClick -> {
                _state.value = _state.value.copy(
                    volume = 2
                )
            }

            CreateOrderEvents.OnTimeSwitch -> {
                _state.value = _state.value.copy(
                    isSpecificTime = !_state.value.isSpecificTime
                )
            }

            CreateOrderEvents.OnPickerClick -> {
                _state.value = _state.value.copy(
                    isTimeInput = true
                )
            }

            is CreateOrderEvents.OnTimeChange -> {
                _state.value = _state.value.copy(
                    timeHours = event.h,
                    timeMinutes = event.m
                )
            }

        }
    }
}