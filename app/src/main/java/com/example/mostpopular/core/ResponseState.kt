package com.example.mostpopular.core

sealed class ResponseState<T> {

    data class Success<T>(val data: T) : ResponseState<T>()

    data class Error(val message: String? = null) : ResponseState<String>()

    object None : ResponseState<Nothing>()
}
