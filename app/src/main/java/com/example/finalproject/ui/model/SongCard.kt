package com.example.finalproject.ui.model

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.viewmodel.SongViewModel

@Composable
fun SongCard(
    song: Song,
    spotify_link: String,
    songObject: SongModel,
    vm: SongViewModel
) {
    val dbHandler = DatabaseHandler(LocalContext.current)
    val opened = remember { mutableStateOf(false) }
    SongDialog(song = song, opened = opened, spotify_link = spotify_link)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 4.dp)
            .clickable(onClick = { opened.value = true }),
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(32.dp)) {
                Text(song.rank.toString(), color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(song.title, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(song.artist, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(32.dp)) {
                IconButton(onClick =
                {
                    song.liked.value = !song.liked.value // The value will always change
                    if (song.liked.value) {
                        dbHandler.likeSong(user_id = vm.getCurrentUserId(), song = songObject)
                    } else {
                        dbHandler.unlikeSong(user_id = vm.getCurrentUserId(), song = songObject)
                    }
                }) {
                    Icon(
                        imageVector = if (song.liked.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = Color.Magenta
                    )
                }
            }
        }
    }
}