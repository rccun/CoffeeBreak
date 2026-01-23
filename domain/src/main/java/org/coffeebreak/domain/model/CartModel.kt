package org.coffeebreak.domain.model

data class CartModel(
    val imageUrl: String,
    val coffeeTitle: String,
    val address: String,
    val date: String,
    val createdAt: String,
    val time: String,
    val coast: Int
)
