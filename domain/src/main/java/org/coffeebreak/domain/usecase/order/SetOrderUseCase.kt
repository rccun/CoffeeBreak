package org.coffeebreak.domain.usecase.order

import org.coffeebreak.domain.model.OrderModel
import org.coffeebreak.domain.repository.OrderRepository

class SetOrderUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(model: OrderModel) = repo.setOrder(model)
}