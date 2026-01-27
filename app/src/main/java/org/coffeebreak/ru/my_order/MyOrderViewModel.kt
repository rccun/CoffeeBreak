package org.coffeebreak.ru.my_order

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import kotlin.math.abs

class MyOrderViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(MyOrderState())
    val state: State<MyOrderState> = _state

    fun onEvent(event: MyOrderEvents) {
        when (event) {
            is MyOrderEvents.OnPaymentChange -> {
                if (_state.value.payment != event.value) {
                    _state.value = _state.value.copy (
                        payment = abs(_state.value.payment - 1)
                    )
                }
            }
        }
    }
}