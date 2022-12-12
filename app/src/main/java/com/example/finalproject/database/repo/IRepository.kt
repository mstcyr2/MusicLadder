package com.example.finalproject.database.repo

import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.database.models.UserModel
import com.example.finalproject.ui.data.Song
import java.util.*
import kotlin.collections.ArrayList

abstract class IRepository {
    abstract fun createTables()
    abstract suspend fun getSongs() : ArrayList<SongModel>
    abstract fun sortByLikes() : ArrayList<SongModel>
    abstract fun filterByGenre(genre : String) : ArrayList<SongModel>
    abstract suspend fun addSong(
        id: String,
        title: String,
        artist: String,
        category: String,
        url: String
    )
    abstract fun getUser(
        user: String,
        pass: String
    ) : String?

    abstract fun getUserName(user_id: String) : String?

    abstract fun addUser(
        user : String,
        pass : String
    ) : String

    abstract fun toggleLike(user_id : String, song: SongModel, isLiked : Boolean)
    abstract fun getUserLikes(user_id : String): ArrayList<String>

    abstract fun addSongToPlaylist(
        playlist_id: String?,
        user_id: String?,
        song_id: String?
    )
    abstract fun deleteSongFromPlaylist(
        user_id: String?,
        song_id: String?
    )
    abstract fun getSongsFromPlaylist(
        playlist_id: String?,
        user_id: String?
    ) : ArrayList<String>
    abstract fun getUserPlaylists(user_id : String) : ArrayList<PlaylistModel>
    abstract fun addNewPlaylist(user_id: String, playlist_name: String)
}