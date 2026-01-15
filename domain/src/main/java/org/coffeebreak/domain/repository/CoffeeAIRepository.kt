package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.CoffeeAIModel

interface CoffeeAIRepository {
    suspend fun generateDescription(input: CoffeeAIModel): String
}