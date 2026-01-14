package org.coffeebreak.domain.usecase.items

import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.utils.CustomResult

class GetItemsUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(): CustomResult<List<ItemModel>> {
        return repo.getItems()
    }
}