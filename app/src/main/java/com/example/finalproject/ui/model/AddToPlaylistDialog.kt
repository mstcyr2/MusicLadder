package com.example.finalproject.ui.model


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.core.content.ContextCompat.startActivity
import com.example.finalproject.R
import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.ui.data.Playlist
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun AddToPlaylistDialog(vm : AppViewModel, opened: MutableState<Boolean>, song_id : String) {
    val playlists by vm.userPlaylists
    val list = playlists.toList()
    if (opened.value) {
        AlertDialog(
            onDismissRequest = { opened.value = false },
            title = { Text("Choose A Playlist") },
            text = {
                LazyColumn(modifier = Modifier.background(Color.Magenta)){
                    itemsIndexed(list) { i, playlist: PlaylistModel ->
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 2.dp),
                            elevation = 5.dp,
                            shape = RectangleShape
                        ) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                                .clickable {
                                    vm.onAddSongToPlaylist(playlist.id, song_id)
                                    opened.value = false
                                },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = playlist.name)
                            }
                        }

                    }
                }
            },
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Button(
                        onClick = { opened.value = false },
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        Text("Cancel",
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