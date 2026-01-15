package org.coffeebreak.data.mapper

import org.coffeebreak.domain.model.CoffeeAIModel


class CoffeePromptMapper {

    fun map(input: CoffeeAIModel): String =
        buildString {
            append("Coffee: ${input.type}. ")
            input.country?.let { append("Country: $it. ") }
            input.sort?.let { append("Roast: $it. ") }
            input.milk?.let { append("Milk: $it. ") }
            input.syrup?.let { append("Syrup: $it.") }
        }
}
