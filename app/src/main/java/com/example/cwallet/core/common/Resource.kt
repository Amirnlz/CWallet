package com.example.cwallet.core.common

sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val error: T) : Resource<T>()
    data object Loading : Resource<Nothing>()
}