package com.example.gotcharacters.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.business.usecase.GetGOTCharactersUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class CharactersViewModel @Inject constructor(
    private val getGOTCharactersUsecase: GetGOTCharactersUsecase
) : ViewModel() {

    var state by mutableStateOf(
        CharactersUiState(
            charactersItems = emptyList()
        )
    )

    fun loadDebugOptions() = viewModelScope.launch {
        when(val result = getGOTCharactersUsecase()) {
            GOTCharactersItemsResult.NoInternet -> Unit
            GOTCharactersItemsResult.ServerErrror -> Unit
            is GOTCharactersItemsResult.Success -> {
                state = state.copy(charactersItems = result.items.map{
                    mapToUI(it)
                })
            }
        }
    }

    internal data class CharactersUiState(
        val charactersItems: List<CharactersUi>
    )

}

internal class CharactersUi (
    val name: String,
    val gender: String
)

internal fun mapToUI(result: GOTCharactersResult): CharactersUi = CharactersUi(result.name, result.gender)