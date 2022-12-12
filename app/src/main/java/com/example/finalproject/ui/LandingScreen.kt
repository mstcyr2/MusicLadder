package com.example.finalproject.ui

//import com.example.finalproject.assets.DatabaseConnection
import DatabaseConnection
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.R
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.ui.data.Playlist
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
    val songs by vm.sortedSongs
    val likedSongs by vm.likedSongs
    val list : ArrayList<SongModel> = ArrayList()
    for (song in songs) {
        if (likedSongs.contains(song.song_id)) {
            list.add(song)
        }
    }
    
    

    //DatabaseConnection(vm = vm)

    Scaffold(
        drawerContent = {
            if (currentUser == "") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(30.dp)
                        .fillMaxSize()) {
                    SignInButton(nav = navController)
                }
            } else {
                Text(
                    text = "Log Out",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable {
                            vm.onLogOut()
                            navController.navigate(Routes.Landing.route)
                                   },
                    style = TextStyle(textDecoration = TextDecoration.Underline)
                )
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "profile",
                        modifier = Modifier
                            .size(150.dp)
                            .clickable { },
                        tint = Color.LightGray
                    )
                    Text(name.value, fontSize = 30.sp)
                    Box(modifier = Modifier
                        .padding(16.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text("Liked Songs", fontSize = 24.sp)
                        LazyColumn(modifier = Modifier.background(Color.Magenta)){
                            items(list) { song: SongModel ->
                                val context = LocalContext.current
                                val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(song.spotify_link)) }
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
                                        Text(text = "${song.song_name}\n${song.artist_name}", modifier = Modifier.width(200.dp), overflow = TextOverflow.Ellipsis)
                                        Button(
                                            onClick = {
                                                context.startActivity(intent)
                                            },
                                            colors = ButtonDefaults.buttonColors(Color.White),
                                            modifier = Modifier.size(80.dp, 40.dp)
                                        ) {
                                            Image(painter = painterResource(id = R.drawable.ic_spotify_icon), contentDescription = "Play on Spotify")
                                        }
                                    }
                                }

                            }
                        }
                    }


                }
            }

        },
        scaffoldState = scaffoldState
    ){
        Column (modifier = Modifier.padding(32.dp)){
            Greeting(name = name.value, scope, scaffoldState)
            NavButtons(nav = navController, vm)

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
            textAlign = TextAlign.Start
        )
        IconButton(
            onClick = {scope.launch {
                state.drawerState.open()
            }},
            modifier = Modifier.size(60.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.fillMaxSize(),
                    tint = Color.Blue
                )
        }
    }
}

@Composable
fun NavButtons(
    nav: NavHostController,
    vm: AppViewModel
) {
    val context = LocalContext.current
    val currentUser by vm.currentUser
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { nav.navigate(Routes.Browse.route) }
        ) {
            Text(text = "Browse", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = {
            if (currentUser == "")
                Toast.makeText(context, "Please log in to make playlists", Toast.LENGTH_LONG).show()
            else
                nav.navigate(Routes.PlayList.route)
        }
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}

@Composable
fun SignInButton(nav: NavHostController) {
    Button(
        onClick = { nav.navigate(Routes.LogIn.route) },
    ) {
        Text(text = "Sign In",
            fontSize = 32.sp,
            color = Color.White
        )
    }
}

@Composable
fun TopTen(vm : AppViewModel) {
    val likedSongs by vm.likedSongs
    val displaySongs by vm.topTen


    Row(
        modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = "star",
            modifier = Modifier.size(54.dp),
            tint = Color.Blue
        )
        Text(
            text = "Top 10",
            fontSize = 54.sp,
            color = Color.Blue
        )
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