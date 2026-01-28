package org.coffeebreak.data.utils

import android.util.Patterns
import org.coffeebreak.domain.utils.CustomResult
import org.coffeebreak.domain.utils.EmailValidator

class EmailValidatorImpl(): EmailValidator {
    override fun execute(email: String): CustomResult<Unit>? {
        return if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            null
        } else {
            CustomResult.Error("Введите корректный email")
        }
    }
}