package com.example.finalproject.database.models

data class PlaylistSongsModel(
    var playlist_id: String, // Creates a static playlist model based on gen id
    var user_id: String, // Bound to a user ID
    var song_id: String // new song is added & contained
)