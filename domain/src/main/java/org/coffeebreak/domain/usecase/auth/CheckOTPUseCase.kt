package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.repository.AuthRepository

class CheckOTPUseCase(private val repo: AuthRepository) {
    suspend fun execute(otp: String) = repo.checkOTP(otp)
}