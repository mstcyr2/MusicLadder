package com.example.finalproject.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.data.Playlist
import com.example.finalproject.ui.theme.Shapes

@Composable
fun PlaylistScreen(list: MutableState<List<Playlist>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(),
        ){
            TextField(value = "Search...",
                onValueChange = {},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyColumn(modifier = Modifier.background(Color.Magenta)){
            items(list.value) { playlist: Playlist ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                    elevation = 5.dp,
                    shape = RectangleShape
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = playlist.title)
                        Text(text = playlist.songCount.toString())
                    }
                }

            }
        }
    }
}