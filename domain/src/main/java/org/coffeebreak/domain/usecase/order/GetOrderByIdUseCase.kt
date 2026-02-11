package org.coffeebreak.domain.usecase.order

import org.coffeebreak.domain.repository.OrderRepository

class GetOrderByIdUseCase(private val repo: OrderRepository) {
    suspend fun execute(id: String) = repo.getOrderById(id)
}