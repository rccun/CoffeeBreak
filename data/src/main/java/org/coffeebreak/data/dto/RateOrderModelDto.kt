package org.coffeebreak.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RateOrderModelDto(
    val id: String? = null,
    @SerialName("order_id") val orderId: String,
    val rate: String
)
