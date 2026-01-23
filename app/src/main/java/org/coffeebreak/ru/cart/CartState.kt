package org.coffeebreak.ru.cart

import org.coffeebreak.domain.model.CartModel

data class CartState(
    val page: Int = 1,
    val data: List<CartModel> = emptyList(),
    val isLoading: Boolean = true
)
