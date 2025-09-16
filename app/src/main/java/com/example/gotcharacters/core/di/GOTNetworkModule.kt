package com.example.gotcharacters.core.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object GOTNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitAuthenticated(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideHttpClient(
    ): OkHttpClient {

        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
        return client.build()
    }

    internal class AuthInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
            val requestWithAuthToken = chain.request()
                .newBuilder()
                .addHeader("Authorization", "Bearer 754t!si@glcE2qmOFEcN")
                .build()
            val response = chain.proceed(requestWithAuthToken)
            response
        }
    }
}