package com.example.gotcharacters.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _events = MutableSharedFlow<CharactersUiEvent>()
    val events: Flow<CharactersUiEvent> = _events

    fun loadCharactersList() = viewModelScope.launch {
        when(val result = getGOTCharactersUsecase()) {
            GOTCharactersItemsResult.NoInternet -> _events.emit(CharactersUiEvent.ShowSnackBarError(errorMessageToDisplay))
            GOTCharactersItemsResult.ServerErrror -> _events.emit(CharactersUiEvent.ShowSnackBarError(errorMessageToDisplay))
            is GOTCharactersItemsResult.Success -> {
                _state.value = _state.value.copy(charactersItems = result.items.map{
                    mapToUI(it)
                })
                _events.emit(CharactersUiEvent.ShowSnackBarError(errorMessageToDisplay))
            }
        }
    }

    internal data class CharactersUiState(
        val charactersItems: List<CharactersUi>
    )

    internal sealed class CharactersUiEvent {
        data object Idle: CharactersUiEvent()
        data class ShowSnackBarError(val message: String) : CharactersUiEvent()
    }

}

internal class CharactersUi (
    val name: String,
    val gender: String
)

internal fun mapToUI(result: GOTCharactersResult): CharactersUi = CharactersUi(result.name, result.gender)