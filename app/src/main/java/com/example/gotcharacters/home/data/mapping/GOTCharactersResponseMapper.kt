package com.example.gotcharacters.home.data.mapping

import com.example.gotcharacters.home.data.model.json.GOTCharactersApiJson
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import javax.inject.Inject

internal class GOTCharactersResponseMapper @Inject constructor() {

    private fun GOTCharactersApiJson.toResponse(): GOTCharactersResponse {
        return GOTCharactersResponse(
            name = this.name,
            gender = this.gender,
            culture = this.culture,
            diedDetails = this.diedDetails ?: "",
            titlesList = this.titlesList,
            aliasNames = this.aliasNames,
            seasonsDetails = this.seasonsDetails,
            playedByNames = this.playedByNames,
        )
    }

    fun mapResponse(response: List<GOTCharactersApiJson>): GOTCharacterNetworkResponse.Success {
        val items = response.map {
            it.toResponse()
        }
        return GOTCharacterNetworkResponse.Success(items)
    }
}