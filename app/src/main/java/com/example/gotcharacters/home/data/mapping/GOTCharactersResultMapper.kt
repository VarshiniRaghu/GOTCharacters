package com.example.gotcharacters.home.data.mapping

import com.example.gotcharacters.home.business.model.GOTCharactersItemsResult
import com.example.gotcharacters.home.business.model.GOTCharactersResult
import com.example.gotcharacters.home.data.model.response.GOTCharactersResponse
import javax.inject.Inject

internal class GOTCharactersResultMapper @Inject constructor() {

    private fun GOTCharactersResponse.toResult(): GOTCharactersResult {
        return GOTCharactersResult(
            name = this.name,
            gender = this.gender
        )
    }

    fun mapResult(response: List<GOTCharactersResponse>): GOTCharactersItemsResult.Success {
        val items = response.map {
            GOTCharactersResult(it.name, it.gender)
        }
        return GOTCharactersItemsResult.Success(items)
    }
}