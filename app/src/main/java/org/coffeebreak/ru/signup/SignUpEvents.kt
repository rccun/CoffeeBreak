package org.coffeebreak.ru.signup

interface SignUpEvents {

    data class OnEmailChange(val value: String) : SignUpEvents
    data class OnPasswordChange(val value: String) : SignUpEvents
    data class OnNameChange(val value: String) : SignUpEvents
    data class OnPhoneChange(val value: String) : SignUpEvents
    data object OnShowClick: SignUpEvents
}