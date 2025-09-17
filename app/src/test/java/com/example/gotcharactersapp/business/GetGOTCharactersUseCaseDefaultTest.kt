package com.example.gotcharactersapp.business

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecaseDefault
import com.example.gotcharacters.home.data.mapping.GOTCharactersResultMapper
import com.example.gotcharacters.home.data.model.response.GOTCharacterNetworkResponse
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import com.example.gotcharacters.home.data.repository.GOTCharactersRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class GetGOTCharactersUseCaseDefaultTest {

    private val repository = mockk<GOTCharactersRepository>()
    private val resultMapper = mockk<GOTCharactersResultMapper>()
    private val useCase = GetGOTCharactersUsecaseDefault(repository, resultMapper)

    @Test
    fun `GIVEN the repository returns NoInternet WHEN getting GOT Characters THEN NoInternet should be returned`() =
        runBlocking {
            //GIVEN
            coEvery { repository.getCharactersItems() } returns GOTCharacterNetworkResponse.NoInternet

            //WHEN
            val actual = useCase()
            val expected = GOTCharactersItemsResult.Error

            //THEN
            assertEquals(expected, actual)
        }

    @Test
    fun `GIVEN the repository returns ServerError WHEN getting GOT Characters THEN ServerError should be returned`() =
        runBlocking {
            //GIVEN
            coEvery { repository.getCharactersItems() } returns GOTCharacterNetworkResponse.ServerError

            //WHEN
            val actual = useCase()
            val expected = GOTCharactersItemsResult.Error

            //THEN
            assertEquals(expected, actual)
        }

    @Test
    fun `GIVEN the repository returns Success WHEN getting GOT Characters THEN the result from the mapper should be returned`() =
        runBlocking {
            //GIVEN
            val response = GOTCharacterNetworkResponse.Success(
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

            val usecaseResult = GOTCharactersItemsResult.Success(
                items = listOf(
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

            coEvery { repository.getCharactersItems() } returns response
            every { resultMapper.mapResult(response.items) } returns usecaseResult

            //WHEN
            val actual = useCase()

            //THEN
            assertEquals(usecaseResult, actual)
        }

}