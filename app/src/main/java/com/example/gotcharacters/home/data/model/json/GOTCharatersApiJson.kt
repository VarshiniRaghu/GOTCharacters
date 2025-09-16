package com.example.gotcharacters.home.data.model.json

import com.google.gson.annotations.SerializedName

internal data class GOTCharactersApiJson(

    @SerializedName("name")
    val name: String,

    @SerializedName("gender")
    val gender: String
)