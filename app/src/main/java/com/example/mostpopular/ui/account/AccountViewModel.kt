package com.example.mostpopular.ui.account

import androidx.lifecycle.*
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.core.SharedPreferance
import com.example.mostpopular.authentication.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    val preferance: SharedPreferance
) :
    ViewModel() {

    private val _getUserInfoState: MutableStateFlow<ResponseState<*>> =
        MutableStateFlow(ResponseState.None)
    val getUserInfoState: StateFlow<ResponseState<*>> = _getUserInfoState

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                val result = loginUseCase.invoke(preferance.getEmail())
                _getUserInfoState.emit(ResponseState.Success(result))
            } catch (e: Exception) {

            }
        }
    }
}