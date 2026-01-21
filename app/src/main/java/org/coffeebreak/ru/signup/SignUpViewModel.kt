package org.coffeebreak.ru.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.usecase.auth.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {
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
            SignUpEvents.OnSignUpClick -> {
                viewModelScope.launch{
                    val res = signUpUseCase.execute(UserModel(
                        email = _state.value.email,
                        password = _state.value.password,
                        name = _state.value.name,
                        phone = _state.value.phone,

                    ))
                    _state.value = _state.value.copy (
                        isSuccess = res.isValid
                    )
                }
            }
        }
    }
}