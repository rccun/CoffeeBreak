package org.coffeebreak.ru.construstor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import org.coffeebreak.ru.create_order.CreateOrderEvents
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
            CoffeeConstructorEvents.OnSmallClick -> {
                _state.value = _state.value.copy (
                    roasting = 1
                )
            }
            CoffeeConstructorEvents.OnMediumClick -> {
                _state.value = _state.value.copy (
                    roasting = 2
                )
            }
            CoffeeConstructorEvents.OnLargeClick -> {
                _state.value = _state.value.copy (
                    roasting = 3
                )
            }
            CoffeeConstructorEvents.OnSmallGrindingClick -> {
                _state.value = _state.value.copy (
                    grinding = 0
                )
            }
            CoffeeConstructorEvents.OnLargeGrindingClick -> {
                _state.value = _state.value.copy (
                    grinding = 1
                )
            }
            CoffeeConstructorEvents.OnIce0Click -> {
                _state.value = _state.value.copy (
                    ice = 0
                )
            }
            CoffeeConstructorEvents.OnSmallIceClick -> {
                _state.value = _state.value.copy (
                    ice = 1
                )
            }
            CoffeeConstructorEvents.OnMediumIceClick -> {
                _state.value = _state.value.copy (
                    ice = 2
                )
            }
            CoffeeConstructorEvents.OnLargeIceClick -> {
                _state.value = _state.value.copy (
                    ice = 3
                )
            }
            CoffeeConstructorEvents.OnDismissMenuClick -> {
                _state.value = _state.value.copy (
                    isMilkItems = false,
                    isSyrupItems = false
                )
            }
            CoffeeConstructorEvents.OnMilkItemsClick -> {
                _state.value = _state.value.copy (
                    isMilkItems = true
                )
            }
            CoffeeConstructorEvents.OnSyrupItemsClick -> {
                _state.value = _state.value.copy (
                    isSyrupItems = true
                )
            }
            is CoffeeConstructorEvents.OnSyrupItemClick -> {
                _state.value = _state.value.copy (
                    syrup = event.value,
                    isSyrupItems = false,
                )
            }
            is CoffeeConstructorEvents.OnMilkItemClick -> {
                _state.value = _state.value.copy (
                    milk = event.value,
                    isMilkItems = false
                )
            }
        }
    }
}