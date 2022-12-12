package com.example.finalproject.ui.model


import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.core.content.ContextCompat.startActivity
import com.example.finalproject.R
import com.example.finalproject.ui.data.Playlist
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun PlaylistDialog(vm : AppViewModel, opened: MutableState<Boolean>) {
    val pName = remember { mutableStateOf("") }
    val currentUser by vm.currentUser
    val ctx = LocalContext.current
    if (opened.value) {
        AlertDialog(
            onDismissRequest = { opened.value = false },
            title = { Text("Start New Playlist") },
            text = {
                   TextField(value = pName.value, onValueChange = {pName.value = it}, placeholder = {Text("Name your playlist!")})
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
                        onClick = {
                            if (pName.value != "") {
                                vm.addNewPlaylist(currentUser, pName.value)
                                opened.value = false
                            } else {
                                Toast.makeText(ctx, "Please name your playlist!", Toast.LENGTH_LONG)
                            }

                                  },
                        modifier = Modifier.size(80.dp, 40.dp)
                    ) {
                        Text("Add playlist",
                            fontSize = 10.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }
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