package com.example.gotcharactersapp.data

import com.example.gotcharacters.home.data.mapping.GOTCharactersResponseMapper
import com.example.gotcharacters.home.data.model.json.GOTCharactersApiJson
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import org.junit.Assert.assertEquals
import org.junit.Test

internal class GOTCharacterResponseMapperTest {

    private val mapper = GOTCharactersResponseMapper()

    @Test
    fun `WHEN mapping a GOTCharacter json THEN a response should be returned`() {
        //WHEN
        val response = GOTCharactersApiJson(
            name = "name",
            gender = "gender",
            culture = "culture",
            diedDetails = "diedDetails",
            titlesList = emptyList(),
            aliasNames = emptyList(),
            seasonsDetails = emptyList(),
            playedByNames = emptyList()
        )

        //WHEN
        val actual = mapper.mapResponse(listOf(response))

        //THEN
        val expected = GOTCharacterNetworkResponse.Success(
            listOf(
                GOTCharactersResponse(
                    name = "name",
                    gender = "gender",
                    culture = "culture",
                    diedDetails = "diedDetails",
                    titlesList = emptyList(),
                    aliasNames = emptyList(),
                    seasonsDetails = emptyList(),
                    playedByNames = emptyList()
                )
            )
        )
        assertEquals(expected, actual)
    }
}