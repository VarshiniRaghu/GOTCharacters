package com.example.gotcharacters.home.data.model.response

internal sealed class GOTCharacterNetworkResponse {

    data class Success(
        val items: List<GOTCharactersResponse>
    ): GOTCharacterNetworkResponse()

    data object ServerError: GOTCharacterNetworkResponse()
    data object NoInternet: GOTCharacterNetworkResponse()
}