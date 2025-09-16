package com.example.gotcharacters.home.data.repository

import com.example.gotcharacters.home.data.api.GOTCharactersApi
import com.example.gotcharacters.home.data.mapping.GOTCharactersResponseMapper
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.google.gson.JsonSyntaxException
import java.io.IOException
import javax.inject.Inject

internal class GOTCharactersRepositoryDefault @Inject constructor(
    private val api: GOTCharactersApi,
    private val mapper: GOTCharactersResponseMapper
) : GOTCharactersRepository {

    override suspend fun getCharactersItems(): GOTCharacterNetworkResponse {
        return try {
            val response = api.getCharactersItems()
            if (response.isSuccessful) {
                mapper.mapResponse(response.body()!!)
            } else {
                GOTCharacterNetworkResponse.ServerError
            }
        } catch (e: IOException) {
            GOTCharacterNetworkResponse.NoInternet
        } catch (e: JsonSyntaxException) {
            return GOTCharacterNetworkResponse.ServerError
        }
    }
}