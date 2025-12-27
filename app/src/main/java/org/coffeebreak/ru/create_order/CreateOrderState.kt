package org.coffeebreak.ru.create_order

import org.coffeebreak.domain.model.CoffeeModel

data class CreateOrderState(
    val count: Int = 1,
    val coffee: CoffeeModel? = null,
    val isLoading: Boolean = true,
    val ristrettoOne: Boolean = true,
    val pickupPlace: Int = 0,
    val volume: Int = 0,
    val isSpecificTime: Boolean = true,
    val timeHours: Int = 0,
    val timeMinutes: Int = 0,
    val isTimeInput: Boolean = false
)
