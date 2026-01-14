package org.coffeebreak.domain.usecase.coffee

import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.domain.repository.CoffeeRepository

class GetCoffeeByIdUseCase(
    private val repo: CoffeeRepository
) {
    suspend fun execute(id: String): Result<CoffeeModel> {
        return repo.getCoffeeById(id)
    }
}