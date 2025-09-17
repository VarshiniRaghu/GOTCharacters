package com.example.gotcharacters.home.data.repository

import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse

/** Data logic, error handling and fetching from db if stored, before displaying UI and business calculations goes here*/
internal interface GOTCharactersRepository {
    suspend fun getCharactersItems(): GOTCharacterNetworkResponse
}