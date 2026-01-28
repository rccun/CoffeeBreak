package org.coffeebreak.ru.reset

interface ResetEvents {
    data class OnPasswordChange(val value: String): ResetEvents
    data object OnNextClick: ResetEvents
    data object OnShowClick: ResetEvents
    data object OnCloseDialog: ResetEvents
}