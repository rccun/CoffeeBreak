package org.coffeebreak.ru.two_factor

interface TwoFactorEvents {
    data class OnDigitEntered(/*val index: Int, */val value: String): TwoFactorEvents
    data object OnEnterEnded: TwoFactorEvents
}