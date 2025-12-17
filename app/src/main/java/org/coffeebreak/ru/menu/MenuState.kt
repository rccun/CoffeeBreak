package org.coffeebreak.ru.menu

import org.coffeebreak.domain.model.CoffeeModel

data class MenuState(
    val coffee: CoffeeModel? = null,
    val coffees: List<CoffeeModel> = emptyList(),
    val isLoading: Boolean = true
)
