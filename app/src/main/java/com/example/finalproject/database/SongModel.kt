package com.example.finalproject.database

data class SongModel(
    var song_id: String,
    var song_name: String,
    var artist_name: String,
    var category: String,
    var likes: Int,
    var spotify_link: String
)
