package com.example.gotcharacters.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersViewModel>()
    LaunchedEffect(Unit) { viewModel.loadCharactersList() }
    val snackBarHostState = remember { SnackbarHostState() }
    val charactersUiState by viewModel.state.collectAsStateWithLifecycle()
    var selectedCharacter by remember { mutableStateOf<CharactersUi?>(null) }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is CharactersViewModel.CharactersUiEvent.ShowSnackBarError -> snackBarHostState.showSnackbar(
                    message = "Error"
                )

                is CharactersViewModel.CharactersUiEvent.DisplayCharacterDetails -> {
                    selectedCharacter = event.charactersUi
                }
            }
        }
    }
    selectedCharacter?.let {
        AlertDialog(
            onDismissRequest = { selectedCharacter = null },
            title = { Text(text = it.name) },
            text = {
                Column {
                    Text("Gender: ${it.gender}")
                    Text("Culture: ${it.culture ?: "Unknown"}")
                    Text("Seasons appeared: ${it.seasonsDetails}")
                    Text("Death info: ${it.diedDetails ?: "Alive"}")
                }
            },
            confirmButton = {
                Button(onClick = { selectedCharacter = null }) {
                    Text("Close")
                }
            }
        )
    }
    CharactersListLayout(
        state = charactersUiState, viewModel::setSearchText, viewModel::displayCharacterDetails
    )
}
