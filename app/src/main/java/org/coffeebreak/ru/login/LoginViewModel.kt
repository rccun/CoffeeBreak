package org.coffeebreak.ru.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecase.auth.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun onEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.OnEmailChange -> {
                _state.value = _state.value.copy(
                    email = event.value
                )
            }
            is LoginEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }
            LoginEvents.OnShowClick -> {
                _state.value = _state.value.copy(
                    isShow = !_state.value.isShow
                )
            }
            LoginEvents.OnNextCLick -> {

                viewModelScope.launch(Dispatchers.IO) {
                    val res = signInUseCase.execute(_state.value.email, _state.value.password)
                    if (res.isValid) {
                        _state.value = _state.value.copy (
                            isSuccess = true
                        )
                    } else {
                        _state.value = _state.value.copy (
                            isError = true,
                            errorMessage = res.errorMessage
                        )
                    }
                }
            }
        }
    }
}