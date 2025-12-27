package org.coffeebreak.ru.construstor

import androidx.compose.ui.text.font.FontWeight

interface CoffeeConstructorEvents {
    data class OnSliderChange(val weight: Float): CoffeeConstructorEvents
}