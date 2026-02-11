package org.coffeebreak.ru.current

import android.util.Log
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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.domain.model.FullOrderModel
import org.coffeebreak.domain.usecase.coffee.GetCoffeeByIdUseCase
import org.coffeebreak.domain.usecase.order.GetOrderByIdUseCase
import org.coffeebreak.domain.utils.DatetimeUseCase
import org.coffeebreak.domain.utils.getOrNull
import org.coffeebreak.ru.Route
import javax.inject.Inject

@HiltViewModel
class CurrentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase,
    private val datetimeUseCase: DatetimeUseCase
): ViewModel() {
    val id = savedStateHandle.get<String>("token")?: "blablabla"
    private val _state = mutableStateOf(CurrentState())
    val state: State<CurrentState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getOrderByIdUseCase.execute(id)
            if (res.isValid) {
                val coffee = getCoffeeByIdUseCase.execute(res.getOrNull()!!.coffeeId)
                if (coffee.isSuccess) {
                    _state.value = _state.value.copy (
                        coffee = coffee.getOrNull()
                    )
                }
                val parsedTime = datetimeUseCase.parseDate(res.getOrNull()!!.createdAt!!)
                _state.value = _state.value.copy (
                    order = res.getOrNull()!!,
                    time = "${ parsedTime.first } | ${parsedTime.second} | к ${res.getOrNull()!!.time!!.dropLast(3)}",
                    isLoading = false

                )
            }
        }
    }
}