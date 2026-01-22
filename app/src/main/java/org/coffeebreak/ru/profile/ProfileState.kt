package org.coffeebreak.ru.profile

import org.coffeebreak.domain.model.UserModel

data class ProfileState(
    val user: UserModel? = null,
    val isLoading: Boolean = true
)
