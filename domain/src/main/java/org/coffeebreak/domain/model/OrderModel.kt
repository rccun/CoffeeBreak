package org.coffeebreak.domain.model

data class OrderModel(
    val id: String? = null,
    val userId: String? = null,
    val baristaId: String? = null,
    val coffeeId: String,
    val sortId: String? = null,
    val countryId: String? = null,
    val supplementId: String? = null,
    val count: Int,
    val ristretto: String,
    val place: String,
    val volume: String,
    val roasting: String? = null,
    val grinding: String? = null,
    val milk: String? = null,
    val syrup: String? = null,
    val ice: String? = null,
    val specTime: Boolean,
    val time: String? = null,
    val totalCoast: Long
)
