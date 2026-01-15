package org.coffeebreak.domain.usecase.items

import org.coffeebreak.domain.repository.OrderRepository

class GetItemsByCategoryUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(category: String) = repo.getItemsByCategory(category)
}