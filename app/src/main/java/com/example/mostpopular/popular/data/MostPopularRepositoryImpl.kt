package com.example.mostpopular.popular.data


import com.example.mostpopular.popular.data.model.PopularArticalResponse
import com.example.mostpopular.popular.di.DataService
import com.example.mostpopular.popular.domain.PopularRepository
import retrofit2.Response
import javax.inject.Inject

class MostPopularRepositoryImpl @Inject constructor(
    private val dataService: DataService
) : PopularRepository {

    override suspend fun getPopularArticle(): Response<PopularArticalResponse> {
        return dataService.getPopular()
    }
}