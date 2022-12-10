package com.example.finalproject.ui

import android.database.sqlite.SQLiteException
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.assets.DatabaseConnection
import com.example.finalproject.database.DatabaseHandler
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

    Scaffold(
        drawerContent = {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()) {
                Text(text = "Sign In", fontSize = 32.sp, textDecoration = TextDecoration.Underline)
            }
        },
        scaffoldState = scaffoldState
    ){
        Column (modifier = Modifier.padding(32.dp)){
            Greeting("User", scope, scaffoldState)
            NavButtons(nav = navController)

            DatabaseConnection(vm = vm)

            TopTen(vm = vm)
        }
    }
}

//@Composable
/* fun DatabaseConnection(vm: AppViewModel) {
    val dbHandler = DatabaseHandler(LocalContext.current)

    dbHandler.createMainTables() // TODO: REMOVE THIS AFTER DEBUG

    try {
        dbHandler.addNewSong(
            "rap_1",
            "Superhero (Heroes & Villains)",
            "Metro Boomin, Future, Chris Brown",
            "rap",
            0,
            "https://open.spotify.com/track/0vjeOZ3Ft5jvAi9SBFJm1j?si=fe0ac0217f0d4225"
        )

        dbHandler.addNewSong(
            "rap_2",
            "Rich Flex",
            "Drake, 21 Savage",
            "rap",
            0,
            "https://open.spotify.com/track/1bDbXMyjaUIooNwFE9wn0N?si=0028bba9598b4128"
        )

        dbHandler.addNewSong(
            "rap_3",
            "To The Bone",
            "Quavo, Takeoff, YoungBoy",
            "rap",
            0,
            "https://open.spotify.com/track/4wRJHXHDJnKSPr9IVn0BFR?si=6c882032eae642ad"
        )

        dbHandler.addNewSong(
            "rap_4",
            "Niagara Falls",
            "Metro Boomin, Travis Scott, 21 Savage",
            "rap",
            0,
            "https://open.spotify.com/track/4WuOWVnAqvEQxgSRrspBgt?si=4c4d8ecbd72a43a9"
        )

        dbHandler.addNewSong(
            "rap_5",
            "Hell Yeah",
            "SoFaygo, Ken Carson",
            "rap",
            0,
            "https://open.spotify.com/track/4WuOWVnAqvEQxgSRrspBgt?si=401c61fd839848f3"
        )

        dbHandler.addNewSong(
            "rap_6",
            "One Up",
            "Central Cee",
            "rap",
            0,
            "https://open.spotify.com/track/6R6ZS5HYH4RdXkjEwEJO5R?si=01a131a8c7ff4585"
        )

        dbHandler.addNewSong(
            "rap_7",
            "Letter to Takeoff",
            "Gucci Mane",
            "rap",
            0,
            "https://open.spotify.com/track/7B09THlbQE2RndpgXeXQYE?si=45df0b62186741c1"
        )

        dbHandler.addNewSong(
            "rap_8",
            "Not Finished",
            "Lil Baby",
            "rap",
            0,
            "https://open.spotify.com/track/2A0G0bPmlkUXUbvWqrAXzg?si=a2cd525bc9e54f5d"
        )

        dbHandler.addNewSong(
            "rap_9",
            "Raindrops",
            "Metro Boomin, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/1pacwLXyRO47ka0v6LTIiY?si=83781a05c6d94ade"
        )

        dbHandler.addNewSong(
            "rap_10",
            "One Mic, One Gun",
            "Metro Boomin, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/7CpXFEjLntpr4GdOhTtFEv?si=a9a5640760db41ee"
        )

        dbHandler.addNewSong(
            "rap_11",
            "Just Wanna Rock",
            "Lil Uzi Vert",
            "rap",
            0,
            "https://open.spotify.com/track/4FyesJzVpA39hbYvcseO2d?si=2129b1cea8714ec0"
        )

        dbHandler.addNewSong(
            "rap_12",
            "Do It Again",
            "NLE Choppa, 2Rare",
            "rap",
            0,
            "https://open.spotify.com/track/5gAwpwuchaCGnJLlBMGBzp?si=2d84b1bbc94a471b"
        )

        dbHandler.addNewSong(
            "rap_13",
            "Pussy & Millions",
            "Drake, 21 Savage, Travis Scott",
            "rap",
            0,
            "https://open.spotify.com/track/2KLwPaRDOB87XOYAT2fgxh?si=f5efaa500a0347d0"
        )

        dbHandler.addNewSong(
            "rap_14",
            "Jigsaw",
            "Key Glock",
            "rap",
            0,
            "https://open.spotify.com/track/3Wqs6XFKsKcTjaWhmB8VCP?si=9f92f780037a46ac"
        )

        dbHandler.addNewSong(
            "rap_15",
            "Ain't Safe",
            "Trippie Redd",
            "rap",
            0,
            "https://open.spotify.com/track/6ja11GoXgF75QkEVqqAadn?si=6194811457cf49c7"
        )

        dbHandler.addNewUser(
            "user_1",
            "admin",
            "password"
        )
    } catch(e : SQLiteException) {
        // Log.d("MusicLadder", e.toString())
    }

    try {
        dbHandler.addNewSong(
            "pop_1",
            "Escapism",
            "RAYE, 070 SHAKE",
            "pop",
            0,
            "https://open.spotify.com/track/5Z2MiIZ5I3jJvvmeWMLbOQ?si=c16c71376d504bd0"
        )

        dbHandler.addNewSong(
            "pop_2",
            "Nonsense",
            "Sabrina Carpenter",
            "pop",
            0,
            "https://open.spotify.com/track/6dgUya35uo964z7GZXM07g?si=1dca752450c1495d"
        )

        dbHandler.addNewSong(
            "pop_3",
            "Sorry To Me Too",
            "Julia Michaels",
            "pop",
            0,
            "https://open.spotify.com/track/4SDccIA3pVkjaZsivDPaV5?si=5eab6b73d6b44878"
        )

        dbHandler.addNewSong(
            "pop_4",
            "Pointless",
            "Lewis Capaldi",
            "pop",
            0,
            "https://open.spotify.com/track/4JBiO7wRnE6ueszEUpo347?si=363131dde1e2480f"
        )

        dbHandler.addNewSong(
            "pop_5",
            "One Life",
            "Dermot Kennedy",
            "pop",
            0,
            "https://open.spotify.com/track/6bB4AiK5tH13695FcNGjDY?si=f90ced27b0ef4adf"
        )
    } catch (e: SQLiteException) {
        // Log.d("MusicLadder", e.toString())
    }

    vm.setCurrentUserId("user_1") // TODO MOST IMPORTANT FOR WHEN SOMEONE LOGS IN
} */

@Composable
fun Greeting(name: String, scope: CoroutineScope, state: ScaffoldState) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 8.dp)) {
        Column (modifier = Modifier.size(width = 300.dp, height = 54.dp)){
            Text(text = "Welcome $name!", fontSize = 32.sp)
        }
        Column (modifier = Modifier.size(width = 54.dp, height = 54.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
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
}

@Composable
fun NavButtons(
    nav: NavHostController
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { nav.navigate(Routes.Browse.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Browse", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { nav.navigate(Routes.PlayList.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}

@Composable
fun TopTen(vm : AppViewModel) {
    val dbHandler = DatabaseHandler(LocalContext.current)
    val songs: List<SongModel>
    val likedSongs: List<String>

    songs = dbHandler.getOrderedByLikes()
    likedSongs = dbHandler.getLikedSongs(vm.currentUser.value)

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