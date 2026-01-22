package org.coffeebreak.data.repository

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import org.coffeebreak.domain.repository.QRRepository
import org.coffeebreak.domain.utils.CustomResult
import java.io.ByteArrayOutputStream

class QRRepositoryImpl(): QRRepository {
    override suspend fun generateQR(qrColor: Int, bgColor: Int): CustomResult<ByteArray> {
        val size = 256
        val hints = mapOf(
            EncodeHintType.MARGIN to 0  // убираем белую рамку
        )
        val bitMatrix: BitMatrix = MultiFormatWriter().encode(
            "https://www.figma.com/design/Mcn5tkim0m2PvlFUlL7931/Coffee-break?node-id=306-416&t=WaZQfq1Tq4mutbwL-0 ",
            BarcodeFormat.QR_CODE,
            size,
            size,
            hints
        )
        val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
        for (x in 0 until size) {
            for (y in 0 until size) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) qrColor else bgColor)
            }
        }
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return CustomResult.Success(stream.toByteArray())
    }
}