package org.coffeebreak.ru.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.coffeebreak.domain.usecase.session.GetActiveSessionUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getActiveSessionUseCase: GetActiveSessionUseCase
) : ViewModel() {
    private val _isAuth = MutableStateFlow(false)
    val isAuth = _isAuth.onStart {
        val res = getActiveSessionUseCase.execute()
        _isAuth.update { res != null }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), initialValue = false)

}