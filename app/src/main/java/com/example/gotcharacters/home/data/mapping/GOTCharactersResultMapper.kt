package com.example.gotcharacters.home.data.mapping

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import javax.inject.Inject

internal class GOTCharactersResultMapper @Inject constructor() {

    private fun GOTCharactersResponse.toResult(): GOTCharactersResult {
        return GOTCharactersResult(
            name = this.name,
            gender = this.gender,
            culture = this.culture,
            diedDetails = this.diedDetails,
            titlesList = this.titlesList,
            aliasNames = this.aliasNames,
            seasonsDetails = convertToRomanNumerals(this.seasonsDetails),
            playedByNames = this.playedByNames
        )
    }

    fun mapResult(response: List<GOTCharactersResponse>): GOTCharactersItemsResult.Success {
        val items = response.map {
            it.toResult()
        }
        return GOTCharactersItemsResult.Success(items)
    }

    private fun convertToRomanNumerals(seasons: List<String>?): String {
        val allSeasons = seasons?.map { season ->
            val seasonNumber = season.filter { it.isDigit() }
            val seasonInRoman = when (seasonNumber) {
                "1" -> "I"
                "2" -> "II"
                "3" -> "III"
                "4" -> "IV"
                "5" -> "V"
                "6" -> "VI"
                "7" -> "VII"
                "8" -> "VIII"
                else -> seasonNumber
            }
            seasonInRoman
        }

        return "Season $allSeasons"

    }
}