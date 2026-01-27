package org.coffeebreak.ru.my_order

interface MyOrderEvents {
    data class OnPaymentChange(val value: Int): MyOrderEvents
}