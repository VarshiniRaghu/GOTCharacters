package com.example.gotcharacters.home.business.model

internal sealed class GOTCharactersItemsResult {

    data class Success(
        val items: List<GOTCharactersResult>
    ): GOTCharactersItemsResult()

    //Both server error and no internet error are handled similarly
    data object Error: GOTCharactersItemsResult()
}