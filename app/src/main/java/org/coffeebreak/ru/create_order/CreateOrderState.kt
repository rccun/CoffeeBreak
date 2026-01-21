package org.coffeebreak.ru.create_order

import org.coffeebreak.domain.model.CoffeeModel

data class CreateOrderState(
    val isSuccess: Boolean = false,
    val isCreateSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = true,

//    val coffeeId: String = "",
    val count: Int = 1,
    val coffee: CoffeeModel? = null,
    val ristrettoOne: Boolean = true,
    val pickupPlace: Int = 0,
    val volume: Int = 0,
    val isSpecificTime: Boolean = true,
    val timeHours: String = "",
    val timeMinutes: String = "",
    val isTimeInput: Boolean = false,
    val totalCoast: Int = 100
)
