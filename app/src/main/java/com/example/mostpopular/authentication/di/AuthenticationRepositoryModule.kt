package com.example.mostpopular.authentication.di

import com.example.mostpopular.authentication.data.AuthenticationRepositoryImpl
import com.example.mostpopular.authentication.domain.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthenticationRepositoryModule {

    @Binds
    fun provideAuthenticationRepositoryImpl(favouriteRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

}