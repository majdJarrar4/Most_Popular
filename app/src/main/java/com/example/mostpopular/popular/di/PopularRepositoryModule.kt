package com.example.mostpopular.popular.di

import com.example.mostpopular.popular.data.MostPopularRepositoryImpl
import com.example.mostpopular.popular.domain.PopularRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface PopularRepositoryModule {

    @Binds
    fun providePopularArticleRepositoryImpl(favouriteRepositoryImpl: MostPopularRepositoryImpl): PopularRepository

}