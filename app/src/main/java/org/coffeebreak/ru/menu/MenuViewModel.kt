package org.coffeebreak.ru.menu

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.domain.usecase.coffee.GetCoffeesUseCase
import org.coffeebreak.domain.usecase.user.GetUserByIdUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCoffeesUseCase: GetCoffeesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    val canRegister = combine(_email, _password) {
        Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() &&
                _password.value.length > 8
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeesUseCase.execute()
            val resName = getUserByIdUseCase.execute(null)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        coffees = res.getOrNull()!!,
                        isLoading = false,
                    )
                }
            }
            if (resName.isValid) {
                _state.value = _state.value.copy (
                    userName = resName.getOrNull()!!.name
                )

            }
        }
    }
}