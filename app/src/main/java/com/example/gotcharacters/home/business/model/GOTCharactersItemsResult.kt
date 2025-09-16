package com.example.gotcharacters.home.business.model

internal sealed class GOTCharactersItemsResult {

    data class Success(
        val items: List<GOTCharactersResult>
    ): GOTCharactersItemsResult()

    data object ServerErrror: GOTCharactersItemsResult()
    data object NoInternet : GOTCharactersItemsResult()
}