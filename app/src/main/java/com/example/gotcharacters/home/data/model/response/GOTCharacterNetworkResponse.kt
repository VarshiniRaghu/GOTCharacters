package com.example.gotcharacters.home.data.model.response

/** Sealed class here is used to check if there are any errors */
internal sealed class GOTCharacterNetworkResponse {

    data class Success(
        val items: List<GOTCharactersResponse>
    ): GOTCharacterNetworkResponse()

    data object ServerError: GOTCharacterNetworkResponse()
    data object NoInternet: GOTCharacterNetworkResponse()
}