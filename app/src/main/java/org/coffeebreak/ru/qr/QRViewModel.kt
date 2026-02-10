package org.coffeebreak.ru.qr

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.coffeebreak.domain.usecase.order.GetLastOrderUseCase
import org.coffeebreak.domain.utils.GenerateQR
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val generateQR: GenerateQR,
    private val getLastOrderUseCase: GetLastOrderUseCase
) : ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()
    private val _id = MutableStateFlow<String?>(null)
    val id = _id.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getLastOrderUseCase.execute()
            if (res.isValid) {
                _id.update {
                    res.getOrNull()!!.id
                }
                _loading.update { false }
            }
        }
    }
}