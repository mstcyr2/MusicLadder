package com.example.finalproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finalproject.ui.model.SongCard

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
                    SongCard(rank = i+1, title = "Song", artist = "Artist", liked = remember { mutableStateOf(true) } )
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