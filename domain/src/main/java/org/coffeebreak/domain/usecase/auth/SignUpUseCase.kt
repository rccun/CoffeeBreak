package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.utils.CustomResult

class SignUpUseCase(
    private val repo: AuthRepository
) {
    suspend fun execute(user: UserModel) =
        repo.signUp(user)
}