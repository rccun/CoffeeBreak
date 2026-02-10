package org.coffeebreak.ru.redeem

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.usecase.coffee.GetCoffeesUseCase
import javax.inject.Inject

@HiltViewModel
class RedeemViewModel @Inject constructor(
    private val getCoffeesUseCase: GetCoffeesUseCase
): ViewModel() {
    private val _state  = mutableStateOf(RedeemState())
    val state: State<RedeemState> = _state
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getCoffeesUseCase.execute()
            withContext(Dispatchers.Main) {
                _state.value = _state.value.copy (
                    coffees = res.getOrNull()!!
                )
            }
        }
    }
}