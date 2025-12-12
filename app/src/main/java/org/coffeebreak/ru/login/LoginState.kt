package org.coffeebreak.ru.login

data class LoginState (
    val isSuccess: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isShow: Boolean = false
)
