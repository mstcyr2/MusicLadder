package com.example.finalproject.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
fun PlaylistSongsScreen(
    navController: NavHostController,
    vm: AppViewModel
) {
    PlaylistSongList(navController = navController, vm = vm)
}


@Composable
fun PlaylistSongList(
    navController: NavHostController,
    vm: AppViewModel
) {
    val songs : List<SongModel> by vm.currentPlaylistSongs
    val likedSongs by vm.likedSongs


    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)) {

        BackButton(navController = navController)

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
fun BackButton(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 62.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(modifier = Modifier
            .size(width = 150.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { navController.popBackStack() }
        ) {
            Text(text = "<<< Go Back", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 150.dp, height = 48.dp)
            .padding(end = 8.dp),onClick = {navController.navigate(Routes.Browse.route)}) {
            Text("Add Song")
        }
    }
}