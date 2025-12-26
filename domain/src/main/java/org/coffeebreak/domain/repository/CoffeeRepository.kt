package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.CoffeeModel

interface CoffeeRepository {
    suspend fun getCoffees(): Result<List<CoffeeModel>>
    suspend fun getCoffeeById(id: String): Result<CoffeeModel>
}