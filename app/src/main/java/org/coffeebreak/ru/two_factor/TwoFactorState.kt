package org.coffeebreak.ru.two_factor

data class TwoFactorState(
    val isSuccess: Boolean = false,
    val otp: /*List<*/String/*> = listOf("", "", "", "")*/ = ""
)
