package org.coffeebreak.domain.usecase.barista

import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.utils.CustomResult

class GetBaristasUseCase(
    private val repo: OrderRepository
) {
    suspend fun execute(): CustomResult<List<BaristaModel>> {
        return repo.getBaristas()
    }
}