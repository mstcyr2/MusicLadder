package com.example.finalproject.database.models

data class PlaylistSongsModel(
    val playlist_id: String, // Creates a static playlist model based on gen id
    val user_id: String, // Bound to a user ID
    val song_id: String // new song is added & contained
)