package com.example.mostpopular.popular.domain

import com.example.mostpopular.popular.data.model.PopularArticalResponse
import retrofit2.Response


interface PopularRepository {

    suspend fun getPopularArticle(): Response<PopularArticalResponse>

}