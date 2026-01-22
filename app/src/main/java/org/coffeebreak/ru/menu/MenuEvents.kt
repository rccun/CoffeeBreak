package org.coffeebreak.ru.menu

interface MenuEvents {
    data class OnStarClick(val value: Int): MenuEvents
    data object OnCloseRate: MenuEvents
    data object OnRateClick: MenuEvents
}