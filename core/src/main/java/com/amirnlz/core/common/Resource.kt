package com.amirnlz.core.common


sealed class Resource<T> {
    data class Success<T>(val data: T) : Resource<T>()
    data class Error<T>(val message: String, val exception: Throwable? = null) : Resource<T>()
    data object Loading : Resource<Nothing>()
}