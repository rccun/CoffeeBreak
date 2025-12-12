package org.coffeebreak.ru

import kotlinx.serialization.Serializable

@Serializable
sealed class Route() {
    @Serializable data object Main : Route()
    @Serializable data object Splash : Route()
    @Serializable data object Login : Route()
    @Serializable data object SignUp : Route()
    @Serializable data object StartUp : Route()
    @Serializable data object Cafe : Route()
}
