package org.coffeebreak.ru.forgot

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(ForgotState())
    val state: State<ForgotState> = _state
    fun onEvent(event: ForgotEvents) {
        when (event) {
            is ForgotEvents.OnEmailChange -> {
                _state.value = _state.value.copy (
                    email = event.value
                )
            }
        }
    }
}