package com.example.gotcharacters.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersViewModel @Inject constructor(
    private val getGOTCharactersUsecase: GetGOTCharactersUsecase
) : ViewModel() {

    private val _state = MutableStateFlow(
        CharactersUiState(
            charactersItems = emptyList(),
            filteredItems = emptyList()
        )
    )
    val state: StateFlow<CharactersUiState> = _state
    private val errorMessageToDisplay: String = "Error"

    private val _events = Channel<CharactersUiEvent>()
    val events = _events.receiveAsFlow()

    fun loadCharactersList() = viewModelScope.launch {
        when (val result = getGOTCharactersUsecase()) {
            GOTCharactersItemsResult.Error -> _events.send(
                CharactersUiEvent.ShowSnackBarError(
                    errorMessageToDisplay
                )
            )

            is GOTCharactersItemsResult.Success -> {
                val initialCharactersItems = result.items.map {
                    mapToUI(it)
                }
                _state.update {
                    it.copy(charactersItems = initialCharactersItems, filteredItems = initialCharactersItems)
                }
            }
        }
    }

    /* Used to filter search text from name */
    fun setSearchText(searchText: String) = viewModelScope.launch {
        _state.update { uiState ->
            if (searchText.isEmpty())
                uiState.copy(filteredItems = uiState.charactersItems)
            else uiState.copy(
                filteredItems = _state.value.charactersItems.filter { it.name.contains(searchText) })
        }
    }

    internal data class CharactersUiState(
        val charactersItems: List<CharactersUi>,
        val filteredItems: List<CharactersUi> // Filtered list is used to display searched text results
    )

    internal sealed class CharactersUiEvent {
        data object Idle : CharactersUiEvent()
        data class ShowSnackBarError(val message: String) : CharactersUiEvent()
    }

}

internal class CharactersUi(
    val name: String,
    val gender: String,
    val culture: String,
    val diedDetails: String,

    val titlesList: List<String>,

    val aliasNames: List<String>,

    val seasonsDetails: String,

    val playedByNames: List<String>
)

internal fun mapToUI(result: GOTCharactersResult): CharactersUi =
    CharactersUi(
        name = result.name,
        gender = result.gender,
        culture = result.culture,
        diedDetails = result.diedDetails,
        titlesList = result.titlesList ?: emptyList(),
        aliasNames = result.aliasNames ?: emptyList(),
        seasonsDetails = result.seasonsDetails,
        playedByNames = result.playedByNames ?: emptyList()
    )