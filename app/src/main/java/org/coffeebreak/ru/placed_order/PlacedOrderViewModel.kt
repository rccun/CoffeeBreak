package org.coffeebreak.ru.placed_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import org.coffeebreak.domain.usecase.order.GetOrderInfoUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class PlacedOrderViewModel @Inject constructor(
private val getOrderInfoUseCase: GetOrderInfoUseCase
): ViewModel() {
    private val _info = MutableStateFlow(Triple("", "", ""))
    val info = _info.onStart {
        val res = getOrderInfoUseCase.execute()
        if (res.isValid) {
            res.getOrNull()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Triple("", "", ""))

}