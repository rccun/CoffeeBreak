package org.coffeebreak.domain.usecase.order

import org.coffeebreak.domain.repository.OrderRepository

class SetOrderRateUseCase(private val repo: OrderRepository) {
    suspend fun execute(rate: Int) = repo.setRate(rate)
}