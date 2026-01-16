package org.coffeebreak.data.ai

import org.coffeebreak.data.mapper.CoffeePromptMapper
import org.coffeebreak.domain.model.CoffeeAIModel
import org.coffeebreak.domain.repository.CoffeeAIRepository

class CoffeeAIRepositoryImpl(
//    private val runner: CoffeeModelRunner,
    private val mapper: CoffeePromptMapper
) : CoffeeAIRepository {

    override suspend fun generateDescription(input: CoffeeAIModel): String {
        val prompt = mapper.map(input)
//        return runner.run(prompt)
        return ""
    }
}