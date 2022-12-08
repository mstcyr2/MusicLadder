package com.example.finalproject.ui.model

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat.startActivity
import com.example.finalproject.R
import com.example.finalproject.ui.data.Song

@Composable
fun SongDialog(song: Song, opened: MutableState<Boolean>, spotify_link: String) {
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
                            // startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(spotify_link)))
                        },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        //Text("Play on spotify")
                        Image(painter = painterResource(id = R.drawable.ic_spotify_icon), contentDescription = "Play on Spotify")
                        //Icon(imageVector = Icons.Outlined.PlayArrow, contentDescription = "Play on Spotify", tint = Color.Green)
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color.Magenta),
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
                        colors = ButtonDefaults.buttonColors(Color.Magenta),
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