package com.example.gotcharacters.home.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersViewModel>()
    LaunchedEffect(Unit) { viewModel.loadCharactersList() }
    val snackbarHostState = remember { SnackbarHostState() }
    val charactersUiState by viewModel.state.collectAsStateWithLifecycle()
    val charactersUiEvent by viewModel.events.collectAsState(initial = CharactersViewModel.CharactersUiEvent.Idle)

    LaunchedEffect(charactersUiEvent) {
        when (charactersUiEvent) {
            is CharactersViewModel.CharactersUiEvent.ShowSnackBarError -> snackbarHostState.showSnackbar(
                message = "Error"
            )

            CharactersViewModel.CharactersUiEvent.Idle -> {
                //Do nothing
            }
        }
    }
    CharactersListLayout(
        state = charactersUiState, viewModel::setSearchText
    )
}
