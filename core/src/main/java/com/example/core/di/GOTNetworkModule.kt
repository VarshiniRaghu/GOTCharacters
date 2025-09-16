package com.example.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GOTNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitAuthenticated(@OkHttpAuthenticated httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Retention(AnnotationRetention.BINARY)
    @Qualifier
    public annotation class OkHttpAuthenticated
}