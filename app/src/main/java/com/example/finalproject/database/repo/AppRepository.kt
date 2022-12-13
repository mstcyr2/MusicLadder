package com.example.finalproject.database.repo

import android.content.Context
import com.example.finalproject.database.DatabaseHandler
import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.database.models.SongModel

class AppRepository(context: Context) : IRepository() {

    private val dbHandler : DatabaseHandler = DatabaseHandler(context)

//    override fun createTables() {
//        dbHandler.createMainTables()
//    }

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
        id: String,
        title: String,
        artist: String,
        category: String,
        url: String
    ) {
        dbHandler.addNewSong(
            song_id = id,
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

    override fun addSongToPlaylist(
        playlist_id: String?,
        user_id: String?,
        song_id: String?
    ) {
        dbHandler.addNewSongToPlaylist(playlist_id, user_id, song_id)
    }
    override fun deleteSongFromPlaylist(
        playlist_id: String?,
        user_id: String?,
        song_id: String?
    ) {
        dbHandler.deleteSongFromPlaylist(playlist_id, user_id, song_id)
    }
    override fun getSongsFromPlaylist(
        playlist_id: String?,
        user_id: String?
    ) : ArrayList<String> {
        return dbHandler.getSongsFromPlaylist(playlist_id, user_id)
    }
    override fun getUserPlaylists(user_id : String) : ArrayList<PlaylistModel> {
        return dbHandler.getUserPlaylists(user_id)
    }

    override fun addNewPlaylist(user_id: String, playlist_name: String) {
        dbHandler.addNewPlaylist(user_id, playlist_name)
    }
}