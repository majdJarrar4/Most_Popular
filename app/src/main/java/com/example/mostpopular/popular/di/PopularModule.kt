package com.example.mostpopular.popular.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.mostpopular.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PopularModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson,@ApplicationContext appContext: Context): Retrofit {

       return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getHttpClint(appContext))
            .build()
    }
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    fun getHttpClint(context: Context): OkHttpClient {
        val myCache = Cache(context.cacheDir, 5000000)
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()

        return okHttpClient
    }

    fun hasNetwork(context: Context): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }
    @Provides
    @Singleton
    fun provideFavouriteDao(retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }

}


