package com.example.finalproject.database.repo

import android.content.Context
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.database.models.SongModel

class AppRepository(context: Context) : IRepository() {

    private val dbHandler : DatabaseHandler = DatabaseHandler(context)

    override suspend fun getSongs() : ArrayList<SongModel> {
        return dbHandler.readSongs()
    }

    override fun sortByLikes() : ArrayList<SongModel> {
        return dbHandler.getOrderedByLikes()
    }

    override fun filterByGenre(genre : String): ArrayList<SongModel> {
        return dbHandler.getOrderedByGenre(genre)
    }

    override suspend fun addSong(
        title: String,
        artist: String,
        category: String,
        url: String
    ) {
        dbHandler.addNewSong(
            song_name = title,
            artist_name = artist,
            category = category,
            likes = 0,
            spotify_link = url
        )
    }

    override fun getUser(user: String, pass: String) : String? {
        return dbHandler.findUser(user, pass)
    }

    override fun getUserName(user_id: String) : String? {
        return dbHandler.getUserName(user_id)
    }

    override fun addUser(user : String, pass : String) : String {
        return dbHandler.addNewUser(user, pass)
    }

    override fun toggleLike(user_id: String, song: SongModel, isLiked : Boolean) {
        if (isLiked) {
            dbHandler.unlikeSong(user_id, song)
        } else {
            dbHandler.likeSong(user_id, song)
        }
    }
    override fun getUserLikes(user_id: String): ArrayList<String> {
        return dbHandler.getLikedSongs(user_id)
    }
}