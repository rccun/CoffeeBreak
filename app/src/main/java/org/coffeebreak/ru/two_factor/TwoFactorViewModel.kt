package org.coffeebreak.ru.two_factor

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import io.github.jan.supabase.gotrue.auth
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import javax.inject.Inject

class TwoFactorViewModel @Inject constructor() : ViewModel() {
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
                val response =  client.auth.api.resetPasswordForEmail(email)
            }
        }
    }
}