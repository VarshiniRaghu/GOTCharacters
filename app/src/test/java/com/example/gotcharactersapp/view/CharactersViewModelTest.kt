package com.example.gotcharactersapp.view

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import com.example.gotcharacters.home.ui.CharactersViewModel
import com.example.gotcharacters.home.ui.CharactersViewModel.CharactersUiState
import com.example.gotcharactersapp.core.ViewModelFlowCollector
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContains

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

/*    @Test
    fun `GIVEN use case returns ServerError WHEN Loading Characters THEN SnackBarError event should be emitted`() =
        runTest {
            //GIVEN
            coEvery { mockGetGOTCharactersUsecase() } returns GOTCharactersItemsResult.ServerErrror

            //WHEN
            viewModel.loadCharactersList()

            //THEN
            val expectedEvent = CharactersViewModel.CharactersUiEvent.ShowSnackBarError(errorMessage)

            assertContains(viewModel.events.first().toString(),errorMessage)
        }*/

    @Test
    fun `GIVEN use case returns NoInternet WHEN Loading Characters THEN SnackBarError event should be emitted`() =
        runTest {
            //GIVEN
            coEvery { mockGetGOTCharactersUsecase() } returns GOTCharactersItemsResult.Error

            //WHEN
            viewModel.loadCharactersList()

            //THEN

            assertContains(viewModel.events.first().toString(), errorMessage)
        }


}