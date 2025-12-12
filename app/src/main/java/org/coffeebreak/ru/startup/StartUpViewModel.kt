package org.coffeebreak.ru.startup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StartUpViewModel @Inject constructor(): ViewModel() {
    private val _state = mutableStateOf(StartUpState())
    val state: State<StartUpState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            delay (1500)
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy(
                    isTimeOut = true
                )
            }
        }
    }
}