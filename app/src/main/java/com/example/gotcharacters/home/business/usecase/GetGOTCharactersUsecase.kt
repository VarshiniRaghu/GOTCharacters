package com.example.gotcharacters.home.business.usecase

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult

/** Each feature get/set will have it own usecase and result is handled within implementation */
internal interface GetGOTCharactersUsecase {

    suspend operator fun invoke(): GOTCharactersItemsResult
}