package com.example.finalproject.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryScreen(genre: String) {
    TopFifty(genre = genre)
}

@Composable
fun TopFifty(genre: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)) {
        Row(modifier = Modifier.padding(bottom = 32.dp)) {
            Text(text = genre, fontSize = 32.sp)
        }

        CategoryButtons()

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)) {
            items(50) { i : Int ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)) {
                    com.example.finalproject.SongCard(rank = i+1, title = "Song", artist = "Artist", liked = remember { mutableStateOf(true) } )
                }
            }
        }
    }
}

@Composable
fun SongCard(rank: Int, title: String, artist: String, liked: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 4.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(32.dp)) {
                Text(rank.toString(), color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(title, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(artist, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(32.dp)) {
                IconButton(onClick = { liked.value = !liked.value }) {
                    Icon(
                        imageVector = if (liked.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = Color.Magenta
                    )
                }
            }
        }
    }
}

/**
 * Modified reused function from LandingScreen.kt to modify buttons for UI interfacing
 */
@Composable
fun CategoryButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 64.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Home", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Browse", color = Color.White)
        }
    }
}