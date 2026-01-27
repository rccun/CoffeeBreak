package org.coffeebreak.ru.two_factor

data class TwoFactorState(
    val otp: /*List<*/String/*> = listOf("", "", "", "")*/ = ""
)
