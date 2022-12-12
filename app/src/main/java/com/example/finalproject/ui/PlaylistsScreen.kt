package com.example.finalproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.ui.model.PlaylistDialog
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun PlaylistScreen(navController: NavHostController, vm : AppViewModel) {
    val playlists by vm.userPlaylists
    val list = playlists.toList()
    val opened = remember { mutableStateOf(false) }

    PlaylistDialog(vm = vm, opened = opened)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 32.dp, bottom = 8.dp)
        ) {
            Text("My Playlists", fontSize = 32.sp)
        }

        ButtonsBrowse(navController)

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            TextField(value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                },
                placeholder = { Text(text = "Search for a playlist") }
            )
            Button(onClick = { /*TODO*/ },
                shape = RectangleShape,
                modifier = Modifier.height(56.dp)
            ) {
                Text(text = "Search")
            }
            Button(onClick = { opened.value = true },
                shape = RectangleShape,
                modifier = Modifier.height(56.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add playlist",
                    tint = Color.White
                )
            }
        }
        if (list.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "Nothing to display... Try making a playlist!",
                    fontSize = 32.sp,
                    color = Color.Gray,
                    modifier = Modifier.size(300.dp)
                )
            }
        }
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
                            vm.onOpenPlaylist(playlist.id)
                            navController.navigate(Routes.SongsInPlaylist.route)
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = playlist.name)
                    }
                }

            }
        }
    }
}

/**
 * Modified reused function from LandingScreen.kt to modify buttons for UI interfacing
 */
@Composable
fun ButtonsBrowse(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, top = 11.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { navController.navigate(Routes.Landing.route) },
        ) {
            Text(text = "Home", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { navController.navigate(Routes.Browse.route) },
        ) {
            Text(text = "Browse", color = Color.White)
        }
    }
}