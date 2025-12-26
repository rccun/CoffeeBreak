package org.coffeebreak.ru.create_order

import org.coffeebreak.domain.model.CoffeeModel

data class CreateOrderState(
    val count: Int = 0,
    val coffee: CoffeeModel? = null,
    val isLoading: Boolean = true
)
