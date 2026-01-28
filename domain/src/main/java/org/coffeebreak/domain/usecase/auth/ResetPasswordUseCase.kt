package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.repository.AuthRepository

class ResetPasswordUseCase(private val repo: AuthRepository) {
    suspend fun execute(password: String) = repo.resetPassword(password)
}