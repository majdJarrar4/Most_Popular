package com.example.mostpopular.authentication.data


import com.example.mostpopular.authentication.domain.AuthenticationRepository
import com.example.mostpopular.popular.data.model.PopularArticalResponse
import com.example.mostpopular.popular.di.DataService
import com.example.mostpopular.popular.domain.PopularRepository
import retrofit2.Response
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : AuthenticationRepository {

    override suspend fun loginUser(email: String): UserModel {
        return userDao.loginUser(email)
    }

    override suspend fun registerUSer(user: UserModel) {
        return userDao.registerUser(user)
    }

}