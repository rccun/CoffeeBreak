package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.utils.CustomResult

interface OrderRepository {
    suspend fun getBaristas(): CustomResult<List<BaristaModel>>
    suspend fun getItemsByCategory(category: String): CustomResult<List<ItemModel>>
//    suspend fun getItems
}