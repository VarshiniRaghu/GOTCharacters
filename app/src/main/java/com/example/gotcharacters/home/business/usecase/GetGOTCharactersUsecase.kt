package com.example.gotcharacters.home.business.usecase

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult

internal interface GetGOTCharactersUsecase {

    suspend operator fun invoke(): GOTCharactersItemsResult
}