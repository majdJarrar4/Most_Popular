package com.example.mostpopular.authentication.domain

import com.example.mostpopular.authentication.data.UserModel
import com.example.mostpopular.popular.data.model.PopularArticalResponse
import retrofit2.Response


interface AuthenticationRepository {

    suspend fun loginUser(email:String):UserModel

    suspend fun registerUSer(user:UserModel)

}