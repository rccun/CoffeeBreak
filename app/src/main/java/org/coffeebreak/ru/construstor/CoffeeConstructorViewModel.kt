package org.coffeebreak.ru.construstor

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import org.coffeebreak.data.ai.TextClassificationHelper
import org.coffeebreak.domain.model.CoffeeAIModel
import org.coffeebreak.domain.usecase.coffee.GetCoffeeAIUseCase
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.login.LoginEvents
import org.tensorflow.lite.support.label.Category
import javax.inject.Inject

@HiltViewModel
class CoffeeConstructorViewModel @Inject constructor(
//    private val getCoffeeAIUseCase: GetCoffeeAIUseCase,
//    @ApplicationContext private val con: Context
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(CoffeeConstructorState())
    val state: State<CoffeeConstructorState> = _state

    //    private val listener = object : TextClassificationHelper.TextResultsListener {
//        override fun onResult(results: List<Category>, inferenceTime: Long) {
////
////            activityMainBinding.bottomSheetLayout.inferenceTimeVal.text =
////                String.format("%d ms", inferenceTime)
//            Log.e("TAG", "$results: ");
//        }
//
//        override fun onError(error: String) {
//            Log.e("TAG", "$error: ");
//        }
//    }
//
    init {
        _state.value = _state.value.copy(
            desc = "Бленд, состоящий из 90% арабики и 10% робусты, считается классическим для итальянского эспрессо. Не советуем создавать бленд с содержанием робусты более 30%."
        )
    }

    fun onEvent(event: CoffeeConstructorEvents) {
        when (event) {
            is CoffeeConstructorEvents.OnSliderChange -> {
                _state.value = _state.value.copy(
                    weight = event.weight
                )
            }

            CoffeeConstructorEvents.OnSmallClick -> {
                _state.value = _state.value.copy(
                    roasting = 1
                )
            }

            CoffeeConstructorEvents.OnMediumClick -> {
                _state.value = _state.value.copy(
                    roasting = 2
                )
            }

            CoffeeConstructorEvents.OnLargeClick -> {
                _state.value = _state.value.copy(
                    roasting = 3
                )
            }

            CoffeeConstructorEvents.OnSmallGrindingClick -> {
                _state.value = _state.value.copy(
                    grinding = 0
                )
            }

            CoffeeConstructorEvents.OnLargeGrindingClick -> {
                _state.value = _state.value.copy(
                    grinding = 1
                )
            }

            CoffeeConstructorEvents.OnIce0Click -> {
                _state.value = _state.value.copy(
                    ice = 0
                )
            }

            CoffeeConstructorEvents.OnSmallIceClick -> {
                _state.value = _state.value.copy(
                    ice = 1
                )
            }

            CoffeeConstructorEvents.OnMediumIceClick -> {
                _state.value = _state.value.copy(
                    ice = 2
                )
            }

            CoffeeConstructorEvents.OnLargeIceClick -> {
                _state.value = _state.value.copy(
                    ice = 3
                )
            }

            CoffeeConstructorEvents.OnDismissMenuClick -> {
                _state.value = _state.value.copy(
                    isMilkItems = false,
                    isSyrupItems = false
                )
            }

            CoffeeConstructorEvents.OnMilkItemsClick -> {
                _state.value = _state.value.copy(
                    isMilkItems = true
                )
            }

            CoffeeConstructorEvents.OnSyrupItemsClick -> {
                _state.value = _state.value.copy(
                    isSyrupItems = true
                )
            }

            is CoffeeConstructorEvents.OnSyrupItemClick -> {
                _state.value = _state.value.copy(
                    syrup = event.value,
                    isSyrupItems = false,
                )
            }

            is CoffeeConstructorEvents.OnMilkItemClick -> {
                _state.value = _state.value.copy(
                    milk = event.value,
                    isMilkItems = false
                )
            }

            CoffeeConstructorEvents.OnEncyclopediaClick -> {
                _state.value = _state.value.copy(
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
                _state.value = _state.value.copy(
                    isError = false,
                    errorMessage = ""
                )
            }

            CoffeeConstructorEvents.OnCloseDesc -> {
                _state.value = _state.value.copy(
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





                Route.Constructor.baristaId = null
                Route.Constructor.sortId = null
                Route.Constructor.supplementId = null
            }
        }
    }
}