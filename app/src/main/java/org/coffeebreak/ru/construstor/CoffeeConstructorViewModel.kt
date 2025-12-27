package org.coffeebreak.ru.construstor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class CoffeeConstructorViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(CoffeeConstructorState())
    val state: State<CoffeeConstructorState> = _state
    fun onEvent(event: CoffeeConstructorEvents) {
        when (event) {
            is CoffeeConstructorEvents.OnSliderChange -> {
                _state.value = _state.value.copy (
                    weight = event.weight
                )
            }
        }
    }
}