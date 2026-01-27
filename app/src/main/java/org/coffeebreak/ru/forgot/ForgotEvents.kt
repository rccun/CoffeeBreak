package org.coffeebreak.ru.forgot

interface ForgotEvents {
    data class OnEmailChange(val value: String): ForgotEvents
    data object OnNextClick: ForgotEvents
}