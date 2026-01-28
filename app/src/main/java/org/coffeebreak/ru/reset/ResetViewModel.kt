package org.coffeebreak.ru.reset

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecase.auth.PasswordUseCase
import org.coffeebreak.domain.usecase.auth.ResetPasswordUseCase
import javax.inject.Inject

@HiltViewModel
class ResetViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val passwordUseCase: PasswordUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ResetState())
    val state: State<ResetState> = _state
    fun onEvent(event: ResetEvents) {
        when (event) {
            is ResetEvents.OnPasswordChange -> {
                _state.value = _state.value.copy(
                    password = event.value
                )
            }

            ResetEvents.OnNextClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = passwordUseCase.execute(_state.value.password)
                    if (res == null ) {
                        val res2 = resetPasswordUseCase.execute(_state.value.password)
                        if (res2.isValid) {
                            _state.value = _state.value.copy (
                                isSuccess = true
                            )
                        } else {
                            _state.value = _state.value.copy(
                                isError = true,
                                errorMessage = res2.errorMessage
                            )
                        }
                    } else {
                        _state.value = _state.value.copy(
                            isError = true,
                            errorMessage = res.errorMessage
                        )
                    }
                }
            }
            ResetEvents.OnCloseDialog -> {
                _state.value = _state.value.copy (
                    isError = false,
                    errorMessage = ""
                )
            }
        }
    }
}