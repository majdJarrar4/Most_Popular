package com.example.mostpopular.authentication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mostpopular.core.ResponseState
import com.example.mostpopular.core.SharedPreferance
import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.authentication.domain.usecase.LoginUseCase
import com.example.mostpopular.authentication.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val loginUseCase: LoginUseCase,
    val preferance: SharedPreferance
) :
    ViewModel() {

    private val _userRegisterState: MutableStateFlow<ResponseState<*>> =
        MutableStateFlow(ResponseState.None)
    val userRegisterState: StateFlow<ResponseState<*>> = _userRegisterState

    private val _userLoginState: MutableStateFlow<ResponseState<*>> =
        MutableStateFlow(ResponseState.None)
    val userLoginState: StateFlow<ResponseState<*>> = _userLoginState

    val intentToMainActivity: MutableLiveData<Boolean> = MutableLiveData(false)


    fun registerUser(userModel: UserModel) {
        viewModelScope.launch {
            try {
                val result = registerUseCase(userModel)
                preferance.saveEmailUser(userModel.email)
                intentToMainActivity.postValue(true)
                _userRegisterState.emit(ResponseState.Success(result))

            } catch (e: Exception) {

            }
        }
    }

    fun validateUser(email: String, password: String) {
        viewModelScope.launch {
            try {
                val result = loginUseCase.invoke(email)

                if (result.password.equals(password)) {
                    preferance.saveEmailUser(email)
                    _userLoginState.emit(ResponseState.Success(""))
                    intentToMainActivity.postValue(true)
                } else {
                    _userLoginState.emit(ResponseState.Error(""))
                }
            } catch (e: Exception) {

            }
        }
    }
}