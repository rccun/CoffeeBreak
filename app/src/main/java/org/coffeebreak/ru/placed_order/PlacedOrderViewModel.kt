package org.coffeebreak.ru.placed_order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.coffeebreak.domain.usecase.order.GetOrderInfoUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class PlacedOrderViewModel @Inject constructor(
private val getOrderInfoUseCase: GetOrderInfoUseCase
): ViewModel() {
    private val _info = MutableStateFlow(Triple("", "", ""))
    private val _isTimeOut = MutableStateFlow(false)
    val isTimeOut = _isTimeOut.onStart {
        delay(5000)
        _isTimeOut.update { true }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)
    val info = _info.onStart {
        val res = getOrderInfoUseCase.execute()
        if (res.isValid) {
            _info.update { res.getOrNull()!! }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Triple("", "", ""))

}