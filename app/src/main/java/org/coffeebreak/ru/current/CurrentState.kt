package org.coffeebreak.ru.current

import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.domain.model.FullOrderModel

data class CurrentState(
    val order: FullOrderModel? = null,
    val coffee: CoffeeModel? = null,
    val time: String = "",
    val isLoading: Boolean = true
)
