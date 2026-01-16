package org.coffeebreak.ru.construstor

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
    val isError: Boolean = false,
    val milk: String = "Коровье",
    val syrup: String = "Нет",


    val errorMessage: String = "",
    val isDesc: Boolean = false,
    val desc: String = ""
)
