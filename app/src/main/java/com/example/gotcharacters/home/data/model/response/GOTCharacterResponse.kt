package com.example.gotcharacters.home.data.model.response

internal data class GOTCharactersResponse(
    val name: String,
    val gender: String,
    val culture: String,
    val diedDetails: String,

    val titlesList: List<String>?,

    val aliasNames: List<String>?,

    val seasonsDetails: List<String>?,

    val playedByNames: List<String>?
)