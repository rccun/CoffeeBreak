package org.coffeebreak.ru.two_factor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecase.auth.CheckOTPUseCase
import javax.inject.Inject

@HiltViewModel
class TwoFactorViewModel @Inject constructor(
    private val checkOTPUseCase: CheckOTPUseCase
) : ViewModel() {
    private val _state = mutableStateOf(TwoFactorState())
    val state: State<TwoFactorState> = _state

    fun onEvent(event: TwoFactorEvents) {
        when (event) {
            is TwoFactorEvents.OnDigitEntered -> {
                _state.value = _state.value.copy (
                    otp = event.value
                )
//                val currentOtp = _state.value.otp.toMutableList()
//                if (event.index in currentOtp.indices) {
//                    currentOtp[event.index] = event.value
//                } else {
//                    while (currentOtp.size <= event.index) currentOtp.add("")
//                    currentOtp[event.index] = event.value
//                }
//                _state.value = _state.value.copy(otp = currentOtp)
            }
            TwoFactorEvents.OnEnterEnded -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = checkOTPUseCase.execute(_state.value.otp)
                    if (res.isValid) {
                        _state.value = _state.value.copy (
                            isSuccess = true
                        )
                    }
                }
//                val response =  client.auth.api.resetPasswordForEmail(email)
            }
        }
    }
}