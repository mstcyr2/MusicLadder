package com.example.finalproject.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(onFilter: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        var searchText: String by remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchText,
            onValueChange = { v: String ->
                searchText = v
            },
            placeholder = {
                Text("Search for a playlist")
            },
            singleLine = true
        )
        Button(
            onClick = { onFilter(searchText) },
            modifier = Modifier.padding(start = 10.dp)
        ) {
            Text("Search")
        }
    }
}