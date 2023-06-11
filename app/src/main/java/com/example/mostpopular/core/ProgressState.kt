package com.example.mostpopular.core

sealed class ProgressState {

    object Show : ProgressState()

    object Hide : ProgressState()

    object None : ProgressState()
}