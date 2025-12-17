package org.coffeebreak.domain.utils

sealed class CustomResult<out T> {
    data class Success<T>(val data: T) : CustomResult<T>()
    data class Error(val message: String, val code: Int = -1) : CustomResult<Nothing>()

    val isValid: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    val errorMessage: String get() = if (this is Error) message else ""
    val errorCode: Int get() = if (this is Error) code else -1
}
fun <T> CustomResult<T?>.getOrNull(): T? = when (this) {
    is CustomResult.Success -> data
    is CustomResult.Error -> null
}