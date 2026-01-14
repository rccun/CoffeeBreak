package org.coffeebreak.ru.constructor_order

import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel

data class ConstructorOrderState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val errorMessage: String = "",

    val baristas: List<BaristaModel> = emptyList(),
    val barista: String = "",

    val countries: List<ItemModel> = emptyList(),
    val country: String = "",

    val sorts: List<ItemModel> = emptyList(),
    val sort: String = "",

    val supplements: List<ItemModel> = emptyList(),
    val supplement: String = "",

    val page: Int = 1
)
