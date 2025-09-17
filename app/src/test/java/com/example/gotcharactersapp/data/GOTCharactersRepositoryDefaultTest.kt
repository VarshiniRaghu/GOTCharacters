package com.example.gotcharactersapp.data

import com.example.gotcharacters.home.data.api.GOTCharactersApi
import com.example.gotcharacters.home.data.mapping.GOTCharactersResponseMapper
import com.example.gotcharacters.home.data.model.json.GOTCharactersApiJson
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import com.example.gotcharacters.home.data.repository.GOTCharactersRepositoryDefault
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Response
import java.net.UnknownHostException

@DelicateCoroutinesApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
internal class GOTCharactersRepositoryDefaultTest {

    private val api = mockk<GOTCharactersApi>()
    private val mapper = mockk<GOTCharactersResponseMapper>()
    private val repository = GOTCharactersRepositoryDefault(api, mapper)

    @Test
    fun `GIVEN there is no internet WHEN getting Characters list THEN NoInternet should be returned`() =
        runBlocking {
            //GIVEN
            coEvery { api.getCharactersItems() } throws UnknownHostException("Unable to resolve host")

            //WHEN
            val actual = repository.getCharactersItems()

            //THEN
            assertEquals(GOTCharacterNetworkResponse.NoInternet, actual)
        }

    @Test
    fun `GIVEN there is a server error WHEN getting Characters list THEN ServerError should be returned`() =
        runBlocking {
            //GIVEN
            coEvery { api.getCharactersItems() } returns Response.error(
                500,
                "Internal Server Error".toResponseBody()
            )

            //WHEN
            val actual = repository.getCharactersItems()

            //THEN
            assertEquals(GOTCharacterNetworkResponse.ServerError, actual)
        }

    @Test
    fun `GIVEN the request is successful WHEN getting Characters list THEN success should be returned`() =
        runBlocking {
            //GIVEN
            val response = listOf(
                GOTCharactersApiJson(
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
            coEvery { api.getCharactersItems() } returns Response.success(response)

            val mappedResponse = GOTCharacterNetworkResponse.Success(
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

            every { mapper.mapResponse(response) } returns mappedResponse

            //WHEN
            val actual = repository.getCharactersItems()

            //THEN
            assertEquals(mappedResponse, actual)
        }

}