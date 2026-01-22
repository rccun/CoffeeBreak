package org.coffeebreak.domain.repository

import org.coffeebreak.domain.utils.CustomResult

interface QRRepository {
    suspend fun generateQR(qrColor: Int, bgColor: Int): CustomResult<ByteArray>
}