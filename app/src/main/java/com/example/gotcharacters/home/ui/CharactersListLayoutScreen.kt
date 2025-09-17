package com.example.gotcharacters.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CharactersListLayout(
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
                        onSearch(textFieldState.text.toString()) //Handled locally to filter based on name
                    },
                    expanded = false, //Can be used when search is in different page and results are expanded in same page
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