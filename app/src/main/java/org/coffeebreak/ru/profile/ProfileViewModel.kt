package org.coffeebreak.ru.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.usecase.user.GetUserByIdUseCase
import javax.inject.Inject
@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()


    private val _channel = Channel<String>()
    val channel = _channel.receiveAsFlow()
    private val _user = MutableStateFlow<UserModel?>(null)
    val user = _user.onStart {
        val res = getUserByIdUseCase.execute(null)
        if (res.isValid) {
            _loading.update { false }
            _user.update { it!! }
        } else {
            _channel.send(res.errorMessage)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)
}