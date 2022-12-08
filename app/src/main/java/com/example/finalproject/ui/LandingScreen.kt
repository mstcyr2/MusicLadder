package com.example.finalproject.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.database.SongModel
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.model.SongCard
import com.example.finalproject.ui.nav.NavGraph
import com.example.finalproject.ui.nav.Routes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// TODO: Add NavController
enum class LandingScreenStates() {
    Browse,
    Playlists
}

@Composable
fun LandingScreen(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        drawerContent = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()) {
                Text(text = "Sign In", fontSize = 32.sp, textDecoration = TextDecoration.Underline)
            }
        },
        scaffoldState = scaffoldState
    ){
        Column (modifier = Modifier.padding(32.dp)){
            Greeting("User", scope, scaffoldState)
            NavButtons(nav = navController)
            TopTen()

            // Fake database call for now
            DatabaseConnection()
        }
    }
}

@Composable
fun DatabaseConnection() {
    val dbHandler = DatabaseHandler(LocalContext.current)

    dbHandler.createMainTable()

    dbHandler.addNewSong(
        "genre_rock",
        "artist",
        "genre",
        "",
        1,
        "http://test.com"
    )

    Log.d("MusicLadder", "Added something to the database")

    val songs: List<SongModel>

    songs = dbHandler.readSongs()

    for (i in songs) {
        Log.d("MusicLadder",
            i.song_id + " " + i.song_name + " " + i.artist_name + " "
                    + i.category + " " + i.likes + " " + i.spotify_link
        )
    }
}

@Composable
fun Greeting(name: String, scope: CoroutineScope, state: ScaffoldState) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Column (modifier = Modifier.size(width = 300.dp, height = 54.dp)){
            Text(text = "Welcome $name!", fontSize = 32.sp)
        }
        Column (modifier = Modifier.size(width = 54.dp, height = 54.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            IconButton(
                onClick = {scope.launch {
                    state.drawerState.open()
                }},
                modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Magenta
                )
            }
        }

    }
}

@Composable
fun NavButtons(
    nav: NavHostController
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { nav.navigate(Routes.Browse.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Browse", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { nav.navigate(Routes.PlayList.route) },
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
                SongCard(Song(rank = i+1, title = "Song", artist = "Artist", liked = remember { mutableStateOf(true) }) )
            }
        }
    }
}