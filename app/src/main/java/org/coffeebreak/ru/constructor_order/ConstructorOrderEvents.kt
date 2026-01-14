package org.coffeebreak.ru.constructor_order

import org.coffeebreak.domain.model.BaristaModel

interface ConstructorOrderEvents {
    data class OnBaristaClick(val value: String): ConstructorOrderEvents
    data class OnCountryClick(val value: String): ConstructorOrderEvents
    data class OnSortClick(val value: String): ConstructorOrderEvents
    data class OnSupplementClick(val value: String): ConstructorOrderEvents
    data object OnBackCLick: ConstructorOrderEvents
}