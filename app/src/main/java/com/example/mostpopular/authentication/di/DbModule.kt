package com.example.mostpopular.authentication.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mostpopular.core.SharedPreferance
import com.example.mostpopular.authentication.data.AppDatabase
import com.example.mostpopular.authentication.ui.RegisterFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "user_database")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences(
            RegisterFragment.USER_EMAIL, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) =
        SharedPreferance(preferences)
}