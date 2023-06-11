package com.example.mostpopular.core

data class LocalValidationError(

    var message: String? = null,

    var resourceMessage: Int? = null,

    var viewId: Int? = null
)