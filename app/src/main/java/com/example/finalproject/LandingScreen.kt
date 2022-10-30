package com.example.finalproject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TODO: Add NavController
enum class LandingScreenStates() {
    Browse,
    Playlists
}

@Preview
@Composable
fun LandingScreen() {
    Column (modifier = Modifier.padding(32.dp)){
        Greeting("User")
        Playlist()
        TopTen()
    }
}

@Composable
fun Greeting(name: String) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Column (modifier = Modifier.size(width = 300.dp, height = 54.dp)){
            Text(text = "Welcome $name!", fontSize = 32.sp)
        }
        Column (modifier = Modifier.size(width = 54.dp, height = 54.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                border = BorderStroke(3.dp, color = Color.Magenta)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Magenta
                )
            }
        }

    }
}

@Composable
fun Playlist() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Browse", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}

@Composable
fun TopTen() {
    Row(modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)) {
        Text(text = "Top 10", fontSize = 54.sp)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(10) { i : Int ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)) {
                SongCard(rank = i+1, title = "Song", artist = "Artist", liked = remember { mutableStateOf(true) } )
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
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
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