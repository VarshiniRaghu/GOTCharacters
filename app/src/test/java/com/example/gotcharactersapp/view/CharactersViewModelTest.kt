package com.example.gotcharactersapp.view

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import com.example.gotcharacters.home.ui.CharactersViewModel
import com.example.gotcharacters.home.ui.CharactersViewModel.CharactersUiState
import com.example.gotcharactersapp.core.ViewModelFlowCollector
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class CharactersViewModelTest {
    private val mockGetGOTCharactersUsecase = mockk<GetGOTCharactersUsecase>()

    private lateinit var viewModel: CharactersViewModel

    private lateinit var collector: ViewModelFlowCollector<CharactersUiState, CharactersViewModel.CharactersUiEvent>

    private val errorMessage = "Error"

    @Before
    fun setUp() {

        viewModel = CharactersViewModel(
            mockGetGOTCharactersUsecase
        )
        collector = ViewModelFlowCollector(viewModel.state, viewModel.events)
    }

    @Test
    fun `GIVEN use case returns Success WHEN Loading Characters THEN no event should be emitted`() =
        collector.test { _, events ->
            //GIVEN
            val result = GOTCharactersItemsResult.Success(
                listOf(),
            )
            coEvery { mockGetGOTCharactersUsecase() } returns result

            //WHEN
            viewModel.loadCharactersList()

            //THEN
            assertEquals(emptyList<CharactersViewModel.CharactersUiEvent>(), events)
        }

    @Test
    fun `GIVEN use case returns ServerError WHEN Loading Characters THEN SnackBarError event should be emitted`() =
        collector.test { _, events ->
            //GIVEN
            coEvery { mockGetGOTCharactersUsecase() } returns GOTCharactersItemsResult.ServerErrror

            //WHEN
            viewModel.loadCharactersList()

            //THEN
            val expectedEvents = listOf(
                CharactersViewModel.CharactersUiEvent.ShowSnackBarError(errorMessage)
            )
            assertEquals(expectedEvents, events)
        }

    @Test
    fun `GIVEN use case returns NoInternet WHEN Loading Characters THEN SnackBarError event should be emitted`() =
        collector.test { _, events ->
            //GIVEN
            coEvery { mockGetGOTCharactersUsecase() } returns GOTCharactersItemsResult.NoInternet

            //WHEN
            viewModel.loadCharactersList()

            //THEN
            val expectedEvents = listOf(
                CharactersViewModel.CharactersUiEvent.ShowSnackBarError(errorMessage)
            )
            assertEquals(expectedEvents, events)
        }


}