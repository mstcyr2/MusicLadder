package com.example.finalproject.ui.data

import androidx.compose.runtime.mutableStateOf

val MyPlaylists = listOf<Playlist>(
    Playlist("Rap Playlist", MySongs.size, mutableStateOf(MySongs)),
    Playlist("Pop Playlist", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 3", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 4", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 5", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 6", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 7", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 8", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 9", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 10", MySongs.size, mutableStateOf(MySongs)),
//    Playlist("Playlist 11", MySongs.size, mutableStateOf(MySongs)),
)