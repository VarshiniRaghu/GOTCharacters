package com.example.gotcharactersapp.data

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.data.mapping.GOTCharactersResponseMapper
import com.example.gotcharacters.home.data.mapping.GOTCharactersResultMapper
import com.example.gotcharacters.home.data.model.json.GOTCharactersApiJson
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import org.junit.Assert.assertEquals
import org.junit.Test

internal class GOTCharacterResultMapperTest {

    private val mapper = GOTCharactersResultMapper()

    @Test
    fun `WHEN mapping a GOTCharacter json THEN a response should be returned`() {
        //WHEN
        val response = GOTCharactersResponse(
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
        val actual = mapper.mapResult(listOf(response))

        //THEN
        val expected = GOTCharactersItemsResult.Success(
            listOf(
                GOTCharactersResult(
                    name = "name",
                    gender = "gender",
                    culture = "culture",
                    diedDetails = "diedDetails",
                    titlesList = emptyList(),
                    aliasNames = emptyList(),
                    seasonsDetails = "Season ",
                    playedByNames = emptyList()
                )
            )
        )
        assertEquals(expected, actual)
    }
}