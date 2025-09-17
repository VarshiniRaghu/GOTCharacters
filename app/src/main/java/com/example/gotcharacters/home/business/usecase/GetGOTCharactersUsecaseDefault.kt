package com.example.gotcharacters.home.business.usecase

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.data.mapping.GOTCharactersResultMapper
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.repository.GOTCharactersRepository
import javax.inject.Inject

/** GOTCharactersUSeCase implementation **/
internal class GetGOTCharactersUsecaseDefault @Inject constructor(
    private val repository: GOTCharactersRepository,
    private val resultMapper: GOTCharactersResultMapper
) : GetGOTCharactersUsecase {

    override suspend fun invoke(): GOTCharactersItemsResult {
        return when (val response = repository.getCharactersItems()) {
            //Business logic goes here, converting integers to Roman in mapper
            GOTCharacterNetworkResponse.NoInternet,
            GOTCharacterNetworkResponse.ServerError -> GOTCharactersItemsResult.Error
            is GOTCharacterNetworkResponse.Success -> {
                resultMapper.mapResult(response.items)
            }
        }
    }

}