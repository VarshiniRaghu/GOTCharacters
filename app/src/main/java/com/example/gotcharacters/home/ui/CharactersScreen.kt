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
    val snackBarHostState = remember { SnackbarHostState() }
    val charactersUiState by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is CharactersViewModel.CharactersUiEvent.ShowSnackBarError -> snackBarHostState.showSnackbar(
                    message = "Error"
                )
            }
        }

    }
    CharactersListLayout(
        state = charactersUiState, viewModel::setSearchText
    )
}
