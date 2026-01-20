package org.coffeebreak.domain.model

data class OrderModel(
    val coffeeId: String,
    val count: Int,
    val ristretto: String,
    val place: String,
    val volume: String,
    val specTime: Boolean,
    val time: String? = null,
    val totalCoast: Long,


//    val countryId: String? = null,
//    val baristaId: String? = null,
//    val arabicWeight: Float? = null,
//    val sortId: String? = null,
//    val roasting: String? = null,
//    val grinding: String? = null,
//    val milk: String? = null,
//    val syrup: String? = null,
//    val supplementId: String? = null,
//    val ice: String? = null
)
