package org.coffeebreak.ru.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.session.LogOutUseCase
import org.coffeebreak.domain.usecase.user.GetUserByIdUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val logOutUseCase: LogOutUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ProfileState())
    val state: State<ProfileState> = _state

    //    private val _loading = MutableStateFlow(true)
//    val loading = _loading.asStateFlow()
//
//
//    private val _channel = Channel<String>()
//    val channel = _channel.receiveAsFlow()
//    private val _user = MutableStateFlow<UserModel?>(null)
//    val user = _user.onStart {
//        val res = getUserByIdUseCase.execute(null)
//        if (res.isValid) {
//            _loading.update { false }
//            _user.update { it!! }
//        } else {
//            _channel.send(res.errorMessage)
//        }
//    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), null)
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getUserByIdUseCase.execute(null)
            if (res.isValid) {
                withContext(Dispatchers.Main) {

                    _state.value = _state.value.copy (
                        user = res.getOrNull(),
                        isLoading = false
                    )
                }
            } else {
            }
        }

    }
    fun onLogOutClick() {
        viewModelScope.launch(Dispatchers.IO) {
            logOutUseCase.execute()
        }
    }
}