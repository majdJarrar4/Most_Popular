package com.example.mostpopular.popular.di

import com.example.mostpopular.popular.data.model.PopularArticalResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {

    @GET("30.json")
    suspend fun getPopular(@Query("api-key") key: String= "YUy8I0plIwLFVePrhJdIHiG9IfGzNQI2"): Response<PopularArticalResponse>

}