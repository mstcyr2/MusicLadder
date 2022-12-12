package com.example.finalproject.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.ui.data.Playlist
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun PlaylistsView(
    navController: NavHostController,
    onSearch: (String) -> Unit,
    list: List<Playlist>
) {
    val textList = remember { mutableStateOf(TextFieldValue("")) }
    val items by remember { mutableStateOf(listOf(list.toString())) }
    val searchedText = textList.value.text
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, top = 32.dp, bottom = 8.dp)
        ) {
            Text("My Playlists", fontSize = 32.sp)
        }

        ButtonsBrowse(navController)
        SearchBar(onSearch = onSearch)
        LazyColumn(modifier = Modifier.background(Color.Magenta)){
            items(list) { playlist: Playlist ->
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