package org.coffeebreak.ru.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor () : ViewModel() {
    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay(3000)
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(
                    isTimeOut = true
                )
            }
        }
    }
}