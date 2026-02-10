package org.coffeebreak.ru.redeem

import org.coffeebreak.domain.model.CoffeeModel

data class RedeemState(
    val coffees: List<CoffeeModel> = emptyList()
)
