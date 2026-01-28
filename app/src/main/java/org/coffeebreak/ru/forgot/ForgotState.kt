package org.coffeebreak.ru.forgot

data class ForgotState(
    val isSuccess: Boolean = false,
    val email: String = "",
    val isError: Boolean = false,
    val errorMessage: String = ""
)
