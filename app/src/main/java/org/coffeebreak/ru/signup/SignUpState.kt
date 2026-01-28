package org.coffeebreak.ru.signup

data class SignUpState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val password: String = "",
    val isShow: Boolean = true,
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""

)
