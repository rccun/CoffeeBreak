package org.coffeebreak.domain.model

data class CoffeeAIModel(
    val type: String,
    val country: String? = null,
    val sort: String? = null,
    val supplements: String? = null,
    val milk: String? = null,
    val syrup: String? = null
)
