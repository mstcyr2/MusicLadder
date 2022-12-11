package com.example.finalproject.ui

//import com.example.finalproject.assets.DatabaseConnection
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.ui.data.Song
import com.example.finalproject.ui.model.SongCard
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// TODO: Add NavController
enum class LandingScreenStates() {
    Browse,
    Playlists
}

@Composable
fun LandingScreen(
    navController: NavHostController,
    vm: AppViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val currentUser by vm.currentUser
    val name =  remember { mutableStateOf("User") }

    if (currentUser != "") {
        name.value = vm.getUserName(currentUser).uppercase()
    }

    //DatabaseConnection(vm = vm)

    Scaffold(
        drawerContent = {
            if (currentUser == "") {
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                    .padding(30.dp)
                    .fillMaxWidth()) {
                    Text(text = "Sign In",
                        fontSize = 32.sp,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { navController.navigate(Routes.LogIn.route) }
                    )
                }
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "profile")

                }
            }

        },
        scaffoldState = scaffoldState
    ){
        Column (modifier = Modifier.padding(32.dp)){
            Greeting(name = name.value, scope, scaffoldState)
            NavButtons(nav = navController)

            TopTen(vm = vm)
        }
    }
}

@Composable
fun Greeting(name: String, scope: CoroutineScope, state: ScaffoldState) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)
        .requiredHeight(intrinsicSize = IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Welcome $name!",
            fontSize = 32.sp,
            modifier = Modifier.width(250.dp),
            textAlign = TextAlign.Left
        )
        IconButton(
            onClick = {scope.launch {
                state.drawerState.open()
            }},
            modifier = Modifier.size(48.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Magenta
                )
        }
    }
}

@Composable
fun NavButtons(
    nav: NavHostController
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { nav.navigate(Routes.Browse.route) }
        ) {
            Text(text = "Browse", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { nav.navigate(Routes.PlayList.route) }
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}

@Composable
fun TopTen(vm : AppViewModel) {
    val songs: List<SongModel> by vm.sortedSongs
    val likedSongs by vm.likedSongs

    val displaySongs: ArrayList<SongModel> = ArrayList()

    for (i in 0..9) {
        if (i < songs.size) {
            displaySongs.add(songs[i])
        }
    }

    Row(modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)) {
        Text(text = "Top 10", fontSize = 54.sp)
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(displaySongs) { i, song ->
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