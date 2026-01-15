package org.coffeebreak.domain.usecase.coffee

import org.coffeebreak.domain.model.CoffeeAIModel
import org.coffeebreak.domain.repository.CoffeeAIRepository

class GetCoffeeAIUseCase (
    private val repository: CoffeeAIRepository
) {
    suspend fun execute(input: CoffeeAIModel): String {
        require(input.type.isNotBlank())
        return repository.generateDescription(input)
    }
}
