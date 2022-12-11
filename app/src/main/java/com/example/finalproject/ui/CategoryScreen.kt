package com.example.finalproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.model.SongCard
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel

/**
 * Initializes the Top 50 lists and interfaces
 */
@Composable
fun CategoryScreen(
    navController: NavHostController,
    genre: String,
    vm: AppViewModel
) {
    TopFifty(navController = navController, genre = genre, vm = vm)
}

/**
 * Displays the top 50 list of the given genre, clickable cards
 */
@Composable
fun TopFifty(
    navController: NavHostController,
    genre: String,
    vm: AppViewModel
) {
    val allSongs: List<SongModel> by vm.sortedSongs
    val filteredSongs: List<SongModel> by vm.filteredSongs
    val songs : List<SongModel>
    val likedSongs by vm.likedSongs

    if (genre == "all genres") {
        songs = allSongs
    } else {
        songs = filteredSongs
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)) {
        Row(modifier = Modifier.padding(bottom = 32.dp)) {
            if (genre == "all genres")
                Text(text = "ALL SONGS ", fontSize = 32.sp)
            else
                Text(text = "${genre.uppercase()}'S TOP SONGS ", fontSize = 32.sp)
        }

        CategoryButtons(navController = navController)

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(top = 150.dp)) {
            itemsIndexed(songs) { i, song ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)) {
                    SongCard(Song(
                        rank = i+1,
                        title = song.song_name,
                        artist = song.artist_name,
                        liked = remember { mutableStateOf(likedSongs.contains(song.song_id)) }),
                        spotify_link = song.spotify_link,
                        songObject = song,
                        vm = vm)
                }
            }
        }
    }
}



/**
 * Modified reused function from LandingScreen.kt to modify buttons for UI interfacing
 */
@Composable
fun CategoryButtons(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 62.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(modifier = Modifier
            .size(width = 150.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { navController.navigate(Routes.Browse.route) }
        ) {
            Text(text = "<<< Go Back", color = Color.White)
        }
    }
}