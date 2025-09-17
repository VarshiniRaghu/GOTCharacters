package com.example.gotcharacters.home.business.model

internal sealed class GOTCharactersItemsResult {

    data class Success(
        val items: List<GOTCharactersResult>
    ): GOTCharactersItemsResult()

    data object Error: GOTCharactersItemsResult()
}