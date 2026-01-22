package org.coffeebreak.domain.utils

import org.coffeebreak.domain.repository.QRRepository

class GenerateQR(private val repo: QRRepository) {
    suspend fun execute(qrColor: Int, bgColor: Int) = repo.generateQR(qrColor, bgColor)
}