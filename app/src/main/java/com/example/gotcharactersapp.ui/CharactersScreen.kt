package com.example.gotcharactersapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gotcharactersapp.ui.theme.GOTCharactersAppTheme

@Composable
internal fun CharactersScreen() {
    GOTCharactersAppTheme {
        Card(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Column {
                DisplayText(
                    "Name", 30, Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )
                DisplayText(
                    "Show", 15, Modifier
                        .padding(bottom = 16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun DisplayText(name: String, fontSize: Int, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        textAlign = TextAlign.Center,
        fontSize = fontSize.sp
    )
}

@Preview(showBackground = true)
@Composable
fun DisplayTextPreview() {
    GOTCharactersAppTheme {
        DisplayText("Stark", 30)
    }
}