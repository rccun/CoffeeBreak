package org.coffeebreak.ru.menu

import android.util.Patterns
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.coffee.GetCoffeesUseCase
import org.coffeebreak.domain.usecase.order.SetOrderRateUseCase
import org.coffeebreak.domain.usecase.session.GetActiveSessionUseCase
import org.coffeebreak.domain.usecase.user.GetUserByIdUseCase
import org.coffeebreak.domain.utils.getOrNull
import org.coffeebreak.ru.Route
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCoffeesUseCase: GetCoffeesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getActiveSessionUseCase: GetActiveSessionUseCase,
    private val setOrderRateUseCase: SetOrderRateUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state

    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    val canRegister = combine(_email, _password) {
        Patterns.EMAIL_ADDRESS.matcher(_email.value).matches() &&
                _password.value.length > 8
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), false)

    val isRating: Boolean? = savedStateHandle.toRoute<Route.Menu>().isRating
    init {
        _state.value = _state.value.copy (
            isRating = isRating ?: false
        )
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeesUseCase.execute()

            val session = getActiveSessionUseCase.execute()
            val userId = session?.userId
            
            val resName = getUserByIdUseCase.execute(userId)

            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        coffees = res.getOrNull()!!,
                        isLoading = false,
                    )
                }
            }
            if (resName.isValid) {
                _state.value = _state.value.copy(
                    userName = resName.getOrNull()!!.name
                )
            }
        }
    }
    fun onEvent(event: MenuEvents) {
        when(event) {
            is MenuEvents.OnStarClick -> {
                _state.value = _state.value.copy (
                    rate = event.value
                )
            }
            MenuEvents.OnCloseRate -> {
                _state.value = _state.value.copy (
                    isRating = false
                )
            }
            MenuEvents.OnRateClick -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val res = setOrderRateUseCase.execute(_state.value.rate + 1)


                }
            }
        }
    }
}