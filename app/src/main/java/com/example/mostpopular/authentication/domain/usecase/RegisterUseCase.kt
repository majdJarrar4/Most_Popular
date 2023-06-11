package com.example.mostpopular.authentication.domain.usecase

import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.authentication.domain.AuthenticationRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authenticationRepository: AuthenticationRepository) {

    suspend operator fun invoke(userModel: UserModel) {
        return authenticationRepository.registerUSer(userModel)
    }

}