package org.coffeebreak.ru.cafemap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecase.user.UpdateAddressUseCase
import javax.inject.Inject

@HiltViewModel
class CafeMapViewModel @Inject constructor(
    private val updateAddressUseCase: UpdateAddressUseCase
): ViewModel() {
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()
    fun onEvent(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = updateAddressUseCase.execute(address)
            _isSuccess.update { res.isValid }
        }
    }
}