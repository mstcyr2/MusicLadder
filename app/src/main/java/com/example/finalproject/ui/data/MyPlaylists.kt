package com.example.finalproject.ui.data

import androidx.compose.runtime.mutableStateOf

val MyPlaylists = listOf<Playlist>(
    Playlist("Rap Playlist", MySongs.size, mutableStateOf(MySongs)),
    Playlist("Pop Playlist", MySongs.size, mutableStateOf(MySongs)),
)