package com.example.gotcharacters.home.data.api

import com.example.gotcharacters.home.data.model.json.GOTCharactersApiJson
import retrofit2.Response
import retrofit2.http.GET

internal interface  GOTCharactersApi {

    @GET("characters")
    suspend fun getCharactersItems(
    ): Response<List<GOTCharactersApiJson>>

}