package com.example.gotcharacters.home.data.repository

import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse

internal interface GOTCharactersRepository {
    suspend fun getCharactersItems(): GOTCharacterNetworkResponse
}