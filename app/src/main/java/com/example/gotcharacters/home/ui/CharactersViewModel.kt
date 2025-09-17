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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersViewModel @Inject constructor(
    private val getGOTCharactersUsecase: GetGOTCharactersUsecase
) : ViewModel() {

    private val _state = MutableStateFlow(
        CharactersUiState(
            charactersItems = emptyList()
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
                _state.value = _state.value.copy(charactersItems = result.items.map {
                    mapToUI(it)
                })
            }
        }
    }

    internal data class CharactersUiState(
        val charactersItems: List<CharactersUi>
    )

    internal sealed class CharactersUiEvent {
        data object Idle : CharactersUiEvent()
        data class ShowSnackBarError(val message: String) : CharactersUiEvent()
    }

}

internal class CharactersUi(
    val name: String,
    val gender: String
)

internal fun mapToUI(result: GOTCharactersResult): CharactersUi =
    CharactersUi(result.name, result.gender)