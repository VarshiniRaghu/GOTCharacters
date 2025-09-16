package com.example.gotcharacters.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun CharactersScreen() {
    val viewModel = hiltViewModel<CharactersViewModel>()
    LaunchedEffect(Unit) { viewModel.loadCharactersList()  }
    val snackbarHostState = remember { SnackbarHostState() }
    val charactersUiState by viewModel.state.collectAsStateWithLifecycle()
    val charactersUiEvent by viewModel.events.collectAsState(initial = CharactersViewModel.CharactersUiEvent.Idle)

    LaunchedEffect(charactersUiEvent) {
        when(charactersUiEvent) {
            is CharactersViewModel.CharactersUiEvent.ShowSnackBarError -> snackbarHostState.showSnackbar(message = "Error")
            CharactersViewModel.CharactersUiEvent.Idle -> {
                //Do nothing
            }
        }
    }
    CharactersListLayout(
        state = charactersUiState,
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun CharactersListLayout(
    state: CharactersViewModel.CharactersUiState = CharactersViewModel.CharactersUiState(
        listOf(
            CharactersUi(
                name = "",
                gender = ""
            )
        )
    ),
) {
    Column {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(
                items = state.charactersItems,
                itemContent = {
                    CharactersItem(
                        title = it.name,
                       gender = it.gender
                    )
                }
            )
        }
    }
}

@Composable
private fun CharactersItem(
    title: String = "Name",
    gender: String = "gender"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title
            )
            Text(
                modifier = Modifier.weight(1f),
                text = gender
            )
        }
    }
}