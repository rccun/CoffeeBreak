package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.repository.AuthRepository

class SignInWithGoogleUseCase(private val repo: AuthRepository) {
    suspend fun execute() = repo.signInWithGoogle()
}