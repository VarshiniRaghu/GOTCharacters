package com.example.gotcharactersapp.view

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import com.example.gotcharacters.home.ui.CharactersViewModel
import com.example.gotcharacters.home.ui.CharactersViewModel.CharactersUiState
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContains

@OptIn(ExperimentalCoroutinesApi::class)
internal class CharactersViewModelTest {
    private val mockGetGOTCharactersUsecase = mockk<GetGOTCharactersUsecase>()

    private lateinit var viewModel: CharactersViewModel

    private val dispatcher = UnconfinedTestDispatcher()

    private val errorMessage = "Error"

    @Before
    fun setUp() {

        viewModel = CharactersViewModel(
            dispatcher,
            mockGetGOTCharactersUsecase
        )
    }

    @Test
    fun `GIVEN use case returns Success WHEN Loading Characters THEN no event should be emitted`() =
        runTest {
            backgroundScope.launch(dispatcher) {
                //GIVEN
                val result = GOTCharactersItemsResult.Success(
                    listOf(),
                )

                coEvery { mockGetGOTCharactersUsecase() } returns result

                //WHEN

                viewModel.loadCharactersList()

                //THEN
                assertEquals(
                    viewModel.state.first(), CharactersUiState(
                        emptyList(),
                        emptyList()
                    )
                )
            }
        }

    @Test
    fun `GIVEN use case returns NoInternet WHEN Loading Characters THEN SnackBarError event should be emitted`() =
        runTest {
            backgroundScope.launch(dispatcher) {
                //GIVEN
                coEvery { mockGetGOTCharactersUsecase() } returns GOTCharactersItemsResult.Error

                //WHEN
                viewModel.loadCharactersList()

                //THEN
                assertContains(viewModel.events.first().toString(), errorMessage)
            }
        }


}