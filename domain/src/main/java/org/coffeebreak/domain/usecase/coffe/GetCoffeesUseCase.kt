package org.coffeebreak.domain.usecase.coffe

import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.domain.repository.CoffeeRepository

class GetCoffeesUseCase(
    private val repo: CoffeeRepository
) {
    suspend fun execute(): Result<List<CoffeeModel>> {
        return repo.getCoffees()
    }
}