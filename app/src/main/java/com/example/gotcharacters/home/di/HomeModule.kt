package com.example.gotcharacters.home.di

import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecaseDefault
import com.example.gotcharacters.home.data.repository.GOTCharactersRepository
import com.example.gotcharacters.home.data.repository.GOTCharactersRepositoryDefault
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class HomeModule {

    @ViewModelScoped
    @Binds
    abstract fun bindGOTCharactersUseCase(impl: GetGOTCharactersUsecaseDefault): GetGOTCharactersUsecase

    @ViewModelScoped
    @Binds
    abstract fun bindGOTCharactersRepository(
        impl: GOTCharactersRepositoryDefault
    ): GOTCharactersRepository
}