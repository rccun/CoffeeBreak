package org.coffeebreak.ru.create_order

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.coffe.GetCoffeeByIdUseCase
import javax.inject.Inject
@HiltViewModel
class CreateOrderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase
): ViewModel() {
    private val _state = mutableStateOf(CreateOrderState())
    val state: State<CreateOrderState> = _state

    fun loadCoffee(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeeByIdUseCase.execute(id)
            if (res.isSuccess) {
                withContext(Dispatchers.Main) {
                    _state.value = _state.value.copy(
                        coffee = res.getOrNull()!!,
                        isLoading = false
                    )
                }
            }
            else {
                
            }
        }
    }

    fun onEvent(event: CreateOrderEvents) {
        when (event) {

        }
    }
}