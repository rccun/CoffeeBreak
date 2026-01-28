package org.coffeebreak.domain.utils

interface EmailValidator {
    fun execute(email: String): CustomResult<Unit>?
}