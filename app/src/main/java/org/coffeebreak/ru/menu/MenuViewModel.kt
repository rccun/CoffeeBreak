package org.coffeebreak.ru.menu

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.coffe.GetCoffeesUseCase
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getCoffeesUseCase: GetCoffeesUseCase
): ViewModel() {
    private val _state = mutableStateOf(MenuState())
    val state: State<MenuState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeesUseCase.execute()
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        coffees = res.getOrNull()!!,
                        isLoading = false
                    )
                }
            }
        }
    }
}