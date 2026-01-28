package org.coffeebreak.ru.reset

data class ResetState(
    val password: String = "",
    val isShow: Boolean = false,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""

)
