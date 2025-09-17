package com.example.gotcharacters.home.business.model

internal data class GOTCharactersResult (
    val name: String,
    val gender: String,
    val culture: String,
    val diedDetails: String,

    val titlesList: List<String>?,

    val aliasNames: List<String>?,

    //Seasons details is string which is joined from seasons list
    val seasonsDetails: String,

    val playedByNames: List<String>?
)