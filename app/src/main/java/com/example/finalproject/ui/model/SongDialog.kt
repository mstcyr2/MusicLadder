package com.example.finalproject.ui.model

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.finalproject.R
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun SongDialog(
    song: Song,
    opened: MutableState<Boolean>,
    spotify_link: String,
    song_id: String,
    vm : AppViewModel
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(spotify_link)) }

    val addSong = remember { mutableStateOf(false) }

    AddToPlaylistDialog(vm = vm, opened = addSong, song_id = song_id)

    if (opened.value) {
        AlertDialog(
            onDismissRequest = { opened.value = false },
            title = { Text(song.title) },
            text = { Text(song.artist) },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = {
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        Image(painter = painterResource(id = R.drawable.ic_spotify_icon), contentDescription = "Play on Spotify")
                    }
                    Button(
                        onClick = {
                            opened.value = false
                            addSong.value = true
                        },
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        Text("Add to playlist",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                    Button(
                        onClick = { opened.value = false },
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        Text("Dismiss",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
                }

            },
            modifier = Modifier
                .width(300.dp)
        )
    }

}