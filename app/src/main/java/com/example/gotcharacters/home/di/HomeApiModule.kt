package com.example.gotcharacters.home.di

import com.example.gotcharacters.home.data.api.GOTCharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/* Dependency Injection (dagger, Hilt) class to inject retrofit implementation class */
@Module
@InstallIn(SingletonComponent::class)
internal object HomeApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): GOTCharactersApi =
        retrofit.create(GOTCharactersApi::class.java)

}