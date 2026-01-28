package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.repository.AuthRepository

class SendOTPUseCase(
    private val repo: AuthRepository
) {
    suspend fun execute(email: String) = repo.sendOTP(email)
}