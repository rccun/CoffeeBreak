package org.coffeebreak.ru

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@kotlinx.serialization.json.JsonClassDiscriminator("type")
sealed class Route() {
    @Serializable @SerialName("main") data object Main : Route()
    @Serializable @SerialName("splash") data object Splash : Route()
    @Serializable @SerialName("login") data object Login : Route()
    @Serializable @SerialName("signup") data object SignUp : Route()
    @Serializable @SerialName("startup") data object StartUp : Route()
    @Serializable @SerialName("cafe") data object Cafe : Route()
    @Serializable @SerialName("menu") data object Menu : Route()
    @Serializable @SerialName("gift") data object Gift : Route()
    @Serializable @SerialName("order") data object Order : Route()
    @Serializable @SerialName("create_order") data class CreateOrder(val id: String) : Route()
}
