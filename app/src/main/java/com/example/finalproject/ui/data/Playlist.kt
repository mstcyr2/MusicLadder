package com.example.finalproject.ui.data

import androidx.compose.runtime.MutableState

data class Playlist(
    val title: String,
    val songCount: Int,
    val songs: MutableState<List<Song>>
)