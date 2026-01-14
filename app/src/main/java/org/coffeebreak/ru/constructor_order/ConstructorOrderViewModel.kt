package org.coffeebreak.ru.constructor_order

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.coffeebreak.domain.usecase.barista.GetBaristasUseCase
import org.coffeebreak.domain.usecase.items.GetItemsUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class ConstructorOrderViewModel @Inject constructor(
    private val getBaristasUseCase: GetBaristasUseCase,
    private val getItemsUseCase: GetItemsUseCase
): ViewModel() {
    private val _state = mutableStateOf(ConstructorOrderState())
    val state: State<ConstructorOrderState> = _state

    suspend fun loadData() {
        loadBaristas()
        loadItems()
    }

    suspend fun loadBaristas() {
        val res = getBaristasUseCase.execute()
        if (res.isValid) {
            _state.value = _state.value.copy (
                isLoading = false,
                baristas = res.getOrNull()!!
            )
        }
    }
    suspend fun loadItems() {
        val res = getItemsUseCase.execute()
        if (res.isValid) {
            _state.value = _state.value.copy (
                isLoading = false,
                countries = res.getOrNull()!!.filter { it.category == "countries" },
                sorts = res.getOrNull()!!.filter { it.category == "sorts" },
                supplements = res.getOrNull()!!.filter { it.category == "supplements" },
            )
        } else {
            _state.value = _state.value.copy (
                isError = true,
                errorMessage = res.errorMessage
            )
        }
    }

    fun onEvent(event: ConstructorOrderEvents) {
        when (event) {
            is ConstructorOrderEvents.OnBaristaClick -> {
                _state.value = _state.value.copy (
                    barista = event.value,
                    page = _state.value.page + 1
                )
            }
            ConstructorOrderEvents.OnBackCLick -> {
                _state.value = _state.value.copy (
                    page = _state.value.page - 1
                )
            }
            is ConstructorOrderEvents.OnCountryClick -> {
                _state.value = _state.value.copy (
                    country = event.value
                )
            }
            is ConstructorOrderEvents.OnSortClick -> {
                _state.value = _state.value.copy (
                    sort = event.value
                )
            }
            is ConstructorOrderEvents.OnCountryClick -> {
                _state.value = _state.value.copy (
                    country = event.value
                )
            }
        }
    }
}