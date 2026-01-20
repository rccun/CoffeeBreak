package org.coffeebreak.ru.construstor

import org.coffeebreak.domain.model.CoffeeModel

data class CoffeeConstructorState(
    val weight: Float = 0.5f,
    val roasting: Int = 1,
    val grinding: Int = 0,
    val ice: Int = 0,
    val milkItems: List<String> = listOf(
        "Нет",
        "Коровье",
        "Безлактозное",
        "Обезжиренное",
        "Растительное",
    ),
    val syrupItems: List<String> = listOf(
        "Нет",
        "Амаретто",
        "Кокос",
        "Ваниль",
        "Карамель",
    ),
    val isMilkItems: Boolean = false,
    val isSyrupItems: Boolean = false,


    val barista: String = "",
    val type: String = "",
    val country: String? = null,
    val sort: String? = null,
    val supplements: String? = null,
    val milk: String = "Коровье",
    val syrup: String = "Нет",
    val isDesc: Boolean = false,
    val desc: String = "",




    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = "",
    val isLoading: Boolean = true,

    val count: Int = 1,
    val coffee: CoffeeModel? = null,
    val ristrettoOne: Boolean = true,
    val pickupPlace: Int = 0,
    val volume: Int = 0,
    val isSpecificTime: Boolean = true,
    val timeHours: String = "",
    val timeMinutes: String = "",
    val isTimeInput: Boolean = false,
    val totalCoast: Int = 100
)
