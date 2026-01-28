package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.utils.CustomResult

class ValidateUseCase(
    private val passwordUseCase: PasswordUseCase,
    private val emailUseCase: EmailUseCase
) {
    fun execute(email: String, password: String): CustomResult<Unit> {
        return if (emailUseCase.execute(email) != null) {
            emailUseCase.execute(email)!!
        } else {
            passwordUseCase.execute(password)?: CustomResult.Success(Unit)
        }
    }
}