package org.coffeebreak.domain.usecase.auth

import org.coffeebreak.domain.utils.CustomResult

class PasswordUseCase {
    fun execute(password: String): CustomResult<Unit>? {
        if (password.length < 8) {
            return CustomResult.Error("Пароль должен быть не меньше 8 символов")
        }
        if (!password.any{it.isDigit()}) {
            return CustomResult.Error("Пароль должен содержать цифры")
        }
        if (!password.any{it.isLetter()}) {
            return CustomResult.Error("Пароль должен содержать буквы")
        }
        if (!password.any{it.isUpperCase()}) {
            return CustomResult.Error("Пароль должен содержать заглавные буквы")
        }
        if (!password.any{it.isLowerCase()}) {
            return CustomResult.Error("Пароль должен содержать маленькие буквы")
        }
        if (!password.any{!it.isLetterOrDigit()}) {
            return CustomResult.Error("Пароль должен содержать специальный символ")
        }
        return null
    }
}