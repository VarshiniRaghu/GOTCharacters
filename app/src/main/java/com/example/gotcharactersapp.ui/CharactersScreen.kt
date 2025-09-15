package com.example.gotcharactersapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gotcharactersapp.ui.theme.GOTCharactersAppTheme

@Composable
internal fun CharactersScreen(){
    GOTCharactersAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DisplayText(
                name = "Stark",
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun DisplayText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "GOT Characters $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun DisplayTextPreview() {
    GOTCharactersAppTheme {
        DisplayText("Stark")
    }
}