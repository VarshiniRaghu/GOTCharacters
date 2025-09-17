package com.example.gotcharacters.home.data.model.json

import com.google.gson.annotations.SerializedName

internal data class GOTCharactersApiJson(

    @SerializedName("name")
    val name: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("culture")
    val culture: String,

    @SerializedName("died")
    val diedDetails: String?,

    @SerializedName("titles")
    val titlesList: List<String>,

    @SerializedName("alias")
    val aliasNames: List<String>,

    @SerializedName("tvSeries")
    val seasonsDetails: List<String>,

    @SerializedName("playedBy")
    val playedByNames: List<String>
)