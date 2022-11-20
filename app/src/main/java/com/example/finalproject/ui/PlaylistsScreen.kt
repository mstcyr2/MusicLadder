package com.example.finalproject.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.ui.data.Playlist
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.theme.Shapes

@Composable
fun PlaylistScreen(navController: NavHostController, list: MutableState<List<Playlist>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 32.dp, bottom = 8.dp)
        ) {
            Text("My Playlists", fontSize = 32.sp)
        }

        ButtonsBrowse(navController)

        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        ){
            TextField(value = "Search...",
                onValueChange = {},
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search, contentDescription = "")
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyColumn(modifier = Modifier.background(Color.Magenta)){
            items(list.value) { playlist: Playlist ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                    elevation = 5.dp,
                    shape = RectangleShape
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = playlist.title)
                        Text(text = playlist.songCount.toString())
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
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Home", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { navController.navigate(Routes.Browse.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Browse", color = Color.White)
        }
    }
}