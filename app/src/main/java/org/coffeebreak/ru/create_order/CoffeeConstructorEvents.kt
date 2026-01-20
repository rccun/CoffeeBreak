package org.coffeebreak.ru.create_order

interface CoffeeConstructorEvents {
    data class OnSliderChange(val weight: Float): CoffeeConstructorEvents
    data object OnSmallClick: CoffeeConstructorEvents
    data object OnMediumClick: CoffeeConstructorEvents
    data object OnLargeClick: CoffeeConstructorEvents
    data object OnSmallGrindingClick: CoffeeConstructorEvents
    data object OnLargeGrindingClick: CoffeeConstructorEvents
    data object OnIce0Click: CoffeeConstructorEvents
    data object OnSmallIceClick: CoffeeConstructorEvents
    data object OnMediumIceClick: CoffeeConstructorEvents
    data object OnLargeIceClick: CoffeeConstructorEvents
    data object OnDismissMenuClick: CoffeeConstructorEvents
    data object OnMilkItemsClick: CoffeeConstructorEvents
    data object OnSyrupItemsClick: CoffeeConstructorEvents
    data class OnMilkItemClick(val value: String): CoffeeConstructorEvents
    data class OnSyrupItemClick(val value: String): CoffeeConstructorEvents
    data object OnEncyclopediaClick: CoffeeConstructorEvents
    data object OnCloseDialog: CoffeeConstructorEvents
    data object OnCloseDesc: CoffeeConstructorEvents
    data object OnNextClick: CoffeeConstructorEvents
}