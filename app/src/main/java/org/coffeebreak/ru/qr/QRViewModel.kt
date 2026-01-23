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
import org.coffeebreak.domain.utils.GenerateQR
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class QRViewModel @Inject constructor(
    private val generateQR: GenerateQR
) : ViewModel() {
    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()
    private val _array = MutableStateFlow<ImageBitmap?>(null)
    val array = _array.asStateFlow()
    fun generate(qrColor: Int, bgColor: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = generateQR.execute(qrColor, bgColor)
            if (res.isValid) {
                val bytes = res.getOrNull()!!
                val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                _array.update {
                    bitmap.asImageBitmap()
                }
                _loading.update { false }
            }
        }
    }
}