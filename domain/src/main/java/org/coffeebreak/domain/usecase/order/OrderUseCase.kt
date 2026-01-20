package org.coffeebreak.domain.usecase.order

class OrderUseCase {

    fun parseRistretto(value: Boolean): String {
        return if (value) {
            "1"
        } else {
            "2"
        }
    }

    fun parsePickupPlace(value: Int): String {
        return when(value) {
            1 -> "навынос"
            else -> "на месте"
        }
    }

    fun parseVolume(value: Int) = when (value) {
        1 -> "250"
        2 -> "350"
        3 -> "450"
        else -> ""
    }


}