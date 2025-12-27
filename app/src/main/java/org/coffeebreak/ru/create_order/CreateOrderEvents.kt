package org.coffeebreak.ru.create_order

interface CreateOrderEvents {
    data object OnRisChange: CreateOrderEvents
    data object OnPickupChange: CreateOrderEvents
    data object OnSmallClick: CreateOrderEvents
    data object OnMediumClick: CreateOrderEvents
    data object OnLargeClick: CreateOrderEvents
    data object OnAddClick: CreateOrderEvents
    data object OnDelClick: CreateOrderEvents
    data object OnTimeSwitch: CreateOrderEvents
    data object OnPickerClick: CreateOrderEvents
    data class OnTimeChange(val h: Int, val m: Int): CreateOrderEvents
}