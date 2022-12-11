package com.example.finalproject.ui.model

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun SongCard(
    song: Song,
    spotify_link: String,
    songObject: SongModel,
    vm: AppViewModel
) {
    val context = LocalContext.current
    val currentUser by vm.currentUser
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
                Text(song.rank.toString())
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(song.title, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(song.artist, maxLines = 2, overflow = TextOverflow.Ellipsis)
            }
            Column(modifier = Modifier.width(32.dp)) {
                IconButton(onClick =
                {
                    if (currentUser != "") {
                        vm.onLikeSong(currentUser, songObject, song.liked.value)
                        song.liked.value = !song.liked.value // The value will always change
                    } else
                        Toast.makeText(context, "Please log in to like songs", Toast.LENGTH_LONG).show()
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