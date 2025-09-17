package com.example.gotcharacters.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharactersListLayout(
    state: CharactersViewModel.CharactersUiState = CharactersViewModel.CharactersUiState(
        emptyList(), emptyList()
    ),
    onSearch: (String) -> Unit,
) {
    Column {
        val textFieldState = TextFieldState()
        SearchBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = { textFieldState.edit { replace(0, length, it) } },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                    },
                    expanded = false,
                    onExpandedChange = { //Do nothing
                    },
                    placeholder = { Text("Search") }
                )
            },
            expanded = false,
            onExpandedChange = {
                //Do nothing
            },
        ) {

        }
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(
                items = state.filteredItems,
                itemContent = {
                    CharactersItem(
                        it
                    )
                }
            )
        }
    }
}

@Composable
private fun CharactersItem(
    charactersUi: CharactersUi
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(start = 16.dp)
                .clickable { },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 32.sp,
                text = charactersUi.name
            )
            if (charactersUi.culture.isEmpty().not())
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    text = charactersUi.culture
                )
            if (charactersUi.diedDetails.isEmpty().not())
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 16.sp,
                    text = "Died ${charactersUi.diedDetails}"
                )
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                text = charactersUi.seasonsDetails
            )
        }
    }
}