package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.model.FullOrderModel
import org.coffeebreak.domain.utils.CustomResult

interface OrderRepository {
    suspend fun getBaristas(): CustomResult<List<BaristaModel>>
    suspend fun getItemsByCategory(category: String): CustomResult<List<ItemModel>>
    suspend fun setPreOrder(model: FullOrderModel): CustomResult<Unit>
    suspend fun setOrder(model: FullOrderModel): CustomResult<Unit>
    suspend fun setRate(rate: Int): CustomResult<Unit>
    suspend fun getOrderInfo(): CustomResult<Triple<String, String, String>>
}