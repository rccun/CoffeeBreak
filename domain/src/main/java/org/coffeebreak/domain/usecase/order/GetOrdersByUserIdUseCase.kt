package org.coffeebreak.domain.usecase.order

import org.coffeebreak.domain.repository.OrderRepository

class GetOrdersByUserIdUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute() = repo.getOrdersByUserId()
}