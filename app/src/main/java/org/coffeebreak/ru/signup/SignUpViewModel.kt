package org.coffeebreak.ru.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SignUpViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(SignUpState())
    val state: State<SignUpState> = _state
    fun onEvent(event: SignUpEvents) {
        when (event) {
            is SignUpEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            is SignUpEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }
            is SignUpEvents.OnPhoneChange -> {
                _state.value = _state.value.copy(
                    phone = event.value
                )
            }
            is SignUpEvents.OnNameChange -> {
                _state.value = _state.value.copy(
                    name = event.value
                )
            }
            SignUpEvents.OnShowClick -> {
                _state.value = _state.value.copy(
                    isShow = !_state.value.isShow
                )
            }
        }
    }
}