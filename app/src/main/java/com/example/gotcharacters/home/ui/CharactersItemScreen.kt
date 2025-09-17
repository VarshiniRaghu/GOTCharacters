package com.example.gotcharacters.home.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun CharactersItem(
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
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
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
                    fontWeight = FontWeight.Medium,
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