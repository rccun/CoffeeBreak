package org.coffeebreak.ru

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable
@kotlinx.serialization.json.JsonClassDiscriminator("type")
sealed class Route() {
    @Serializable
    @SerialName("create_order_graph")
    data object CreateOrderGraph : Route()
    @Serializable
    @SerialName("splash")
    data object Splash : Route()
    @Serializable
    @SerialName("login")
    data object Login : Route()
    @Serializable
    @SerialName("signup")
    data object SignUp : Route()
    @Serializable
    @SerialName("startup")
    data object StartUp : Route()
    @Serializable
    @SerialName("cafe")
    data object Cafe : Route()
    @Serializable
    @SerialName("menu")
    data object Menu : Route()
    @Serializable
    @SerialName("gift")
    data object Gift : Route()
    @Serializable
    @SerialName("order")
    data object Order : Route()
    @Serializable
    @SerialName("create_order")
    data class CreateOrder(val id: String? = null) : Route()
    @Serializable
    @SerialName("constructor")
    data object Constructor : Route() {

        var baristaId: String? = null
        var sortId: String? = null
        var supplementId: String? = null
    }

    @Serializable
    @SerialName("country")
    data object Country : Route()
    @Serializable
    @SerialName("sort")
    data object Sort : Route()
    @Serializable
    @SerialName("supplement")
    data object Supplement : Route()
    @Serializable
    @SerialName("barista")
    data object Barista : Route()
    @Serializable
    @SerialName("profile")
    data object Profile : Route()
    @Serializable
    @SerialName("placed_order")
    data object Placed : Route()

}
