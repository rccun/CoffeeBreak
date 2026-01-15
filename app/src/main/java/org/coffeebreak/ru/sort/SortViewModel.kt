package org.coffeebreak.ru.sort

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.domain.usecase.items.GetItemsByCategoryUseCase
import org.coffeebreak.domain.utils.getOrNull
import javax.inject.Inject

@HiltViewModel
class SortViewModel @Inject constructor(
    private val getItemsByCategoryUseCase: GetItemsByCategoryUseCase
): ViewModel() {
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()

    private val _channel = Channel<String>()
    val channel = _channel.receiveAsFlow()

    private val _state = MutableStateFlow<List<ItemModel>>(emptyList())
    val state = _state.onStart {
        val res = getItemsByCategoryUseCase.execute("sorts")
        if (res.isValid) {
            _loading.update {
                false
            }
            _state.update { i ->
                res.getOrNull()!!
            }
        } else {
            _channel.send(res.errorMessage)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), initialValue = emptyList())

}