package com.example.mostpopular.authentication.domain.usecase

import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.authentication.domain.AuthenticationRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(email:String): UserModel {
        return authenticationRepository.loginUser(email)
    }
}