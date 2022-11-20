package com.example.finalproject.ui.data

import androidx.compose.runtime.mutableStateOf

val MySongs = listOf<Song>(
    Song(rank = 1, title = "Song 1", artist = "Artist 1", liked = mutableStateOf(true)),
    Song(rank = 2, title = "Song 2", artist = "Artist 2", liked = mutableStateOf(true)),
    Song(rank = 3, title = "Song 3", artist = "Artist 3", liked = mutableStateOf(true)),
    Song(rank = 4, title = "Song 4", artist = "Artist 4", liked = mutableStateOf(true)),
    Song(rank = 5, title = "Song 5", artist = "Artist 5", liked = mutableStateOf(true)),
    Song(rank = 6, title = "Song 6", artist = "Artist 6", liked = mutableStateOf(true)),
    Song(rank = 7, title = "Song 7", artist = "Artist 7", liked = mutableStateOf(true)),
    Song(rank = 8, title = "Song 8", artist = "Artist 8", liked = mutableStateOf(true)),
    Song(rank = 9, title = "Song 9", artist = "Artist 9", liked = mutableStateOf(true)),
    Song(rank = 10, title = "Song 10", artist = "Artist 10", liked = mutableStateOf(true)),
)