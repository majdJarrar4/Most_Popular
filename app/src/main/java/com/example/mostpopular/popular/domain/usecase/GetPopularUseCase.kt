package com.example.mostpopular.popular.domain.usecase


import com.example.mostpopular.popular.data.model.PopularArticalResponse
import com.example.mostpopular.popular.domain.PopularRepository
import retrofit2.Response
import javax.inject.Inject

class GetUserPopularUseCase @Inject constructor(private val popularRepository: PopularRepository) {

    suspend operator fun invoke(): Response<PopularArticalResponse> {
        return popularRepository.getPopularArticle()
    }
}