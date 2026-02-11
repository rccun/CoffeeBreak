package org.coffeebreak.ru.cart

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.coffeebreak.domain.model.CartModel
import org.coffeebreak.domain.usecase.coffee.GetCoffeeByIdUseCase
import org.coffeebreak.domain.usecase.order.GetOrderInfoUseCase
import org.coffeebreak.domain.usecase.order.GetOrdersByUserIdUseCase
import org.coffeebreak.domain.utils.DatetimeUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject
import kotlin.math.abs

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getOrdersByUserIdUseCase: GetOrdersByUserIdUseCase,
    private val getCoffeeByIdUseCase: GetCoffeeByIdUseCase,
    private val getOrderInfoUseCase: GetOrderInfoUseCase,
    private val datetimeUseCase: DatetimeUseCase

): ViewModel() {
    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val res = getOrdersByUserIdUseCase.execute()
            if (res.isValid ) {
                res.getOrNull()!!.forEach { i ->
                    val res2 = getCoffeeByIdUseCase.execute(i.coffeeId)
                    val userInfo = getOrderInfoUseCase.execute().getOrNull()!!
                    if (res2.isSuccess) {
                        val coffee = res2.getOrNull()!!
                        withContext(Dispatchers.Main) {
                            Log.e("TAG created", "${i.createdAt}");
                            _state.value = _state.value.copy (
                                data = _state.value.data.plus(
                                    CartModel(
                                        imageUrl = coffee.imageUrl,
                                        coffeeTitle = coffee.title,
                                        address = "г. Оренбург, ${userInfo.third}",
                                        date = datetimeUseCase.parseDate(i.createdAt!!).first,
                                        createdAt = datetimeUseCase.parseDate(i.createdAt!!).second,
                                        time = i.time!!,
                                        coast = i.totalCoast.toInt()
                                    )
                                ),
                            )
                        }
                    }
                }
                _state.value = _state.value.copy (
                    isLoading = false
                )
            }
        }

    }

    fun onEvent(event: CartEvents) {
        when (event) {
            CartEvents.OnPageClick -> {
                _state.value = _state.value.copy (
                    page = abs(_state.value.page - 1)
                )
            }
        }
    }
}