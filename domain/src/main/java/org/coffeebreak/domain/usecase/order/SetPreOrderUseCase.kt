package org.coffeebreak.domain.usecase.order

import org.coffeebreak.domain.model.FullOrderModel
import org.coffeebreak.domain.repository.OrderRepository

class SetPreOrderUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(model: FullOrderModel) = repo.setPreOrder(model)
}