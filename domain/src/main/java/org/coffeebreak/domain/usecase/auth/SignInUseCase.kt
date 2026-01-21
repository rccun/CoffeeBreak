package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.repository.AuthRepository

class SignInUseCase(private val repo: AuthRepository) {
    suspend fun execute(email: String, password: String) = repo.signIn(email, password)
}