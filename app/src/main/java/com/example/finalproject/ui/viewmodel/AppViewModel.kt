package com.example.finalproject.ui.viewmodel

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.database.sqlite.SQLiteException
import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.database.models.PlaylistModel
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.database.repo.AppRepository
import com.example.finalproject.database.repo.IRepository
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class AppViewModel(app : Application) : AndroidViewModel(app) {

    private val _currentUser =  mutableStateOf("")
    val currentUser : State<String> = _currentUser

    private val _likedSongs = mutableStateOf(ArrayList<String>())
    val likedSongs = _likedSongs

    private val likesInTT = mutableStateOf(0) //the number of the users liked songs in the top ten

    private val _userPlaylists = mutableStateOf(ArrayList<PlaylistModel>())
    val userPlaylists = _userPlaylists

    private val _currentPlaylistSongs = mutableStateOf(ArrayList<SongModel>())
    val currentPlaylistSongs = _currentPlaylistSongs

    private val _currentPlaylist = mutableStateOf("")
    val currentPlaylist = _currentPlaylist

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory = _selectedCategory

    private val _sortedSongs = mutableStateOf(ArrayList<SongModel>())
    val sortedSongs = _sortedSongs

    private val _filteredSongs = mutableStateOf(ArrayList<SongModel>())
    val filteredSongs = _filteredSongs

    private val _topTen = mutableStateOf(ArrayList<SongModel>())
    val topTen = _topTen

    private lateinit var _repository : IRepository

    init {
        viewModelScope.launch {
            _repository = AppRepository(getApplication())
            //_repository.createTables()
            _sortedSongs.value = _repository.sortByLikes()
            for (i in 0..9) {
                if (i < _sortedSongs.value.size) {
                    _topTen.value.add(_sortedSongs.value[i])
                }
            }

        }
    }

    private fun onSetCurrentUser(user_id: String) {
        likesInTT.value = 0
        if (user_id != "") {
            _currentUser.value = user_id
            _likedSongs.value = getLikedSongs(user_id)
            for (song in _topTen.value) {
                if (_likedSongs.value.contains(song.song_id)) {
                    likesInTT.value += 1
                }
            }
            if (likesInTT.value > 0) {
                val notif = createNotification(likesInTT.value)
                NotificationManagerCompat.from(getApplication<Application>().applicationContext)
                    .notify(likesInTT.hashCode(), notif)
            }
            setUserPlaylists(user_id)

        } else {
            _currentUser.value = user_id
            _likedSongs.value = ArrayList()
        }


    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
        if (category != "all genres") {
            _filteredSongs.value = _repository.filterByGenre(category)
        }
    }

    fun getUserName(user_id: String) : String {
        return _repository.getUserName(user_id)!!
    }

    fun onSignUp(user : String, pass : String) : Boolean {
        try {
            _currentUser.value = _repository.addUser(user, pass)
            Log.d("Try Sign Up: ", "User added successfully")
            return true
        } catch (e : SQLiteException) {
            Log.d("Try Sign Up: ", "User already exists")
            return false
        }
    }

    fun onLogIn(user : String, pass : String) : Boolean {
        try {
            val user_id = _repository.getUser(user, pass)!!
            onSetCurrentUser(user_id)
            return true
        } catch (e : NullPointerException) {
            return false
        }
    }

    fun onLogOut() {
        onSetCurrentUser("")
        likesInTT.value = 0
    }

    private fun getLikedSongs(user_id: String) : ArrayList<String> {
        return _repository.getUserLikes(user_id)
    }


    fun setUserPlaylists(user_id : String) {
        _userPlaylists.value = _repository.getUserPlaylists(user_id)
    }

    fun getUserPlaylistSongs(playlist_id : String, user_id : String) : ArrayList<String> {
        return _repository.getSongsFromPlaylist(playlist_id, user_id)
    }

    fun onOpenPlaylist(playlist_id: String) {
        _currentPlaylistSongs.value = ArrayList<SongModel>()
        _currentPlaylist.value = playlist_id
        val playlistSongs = getUserPlaylistSongs(playlist_id, _currentUser.value)
        for(song in _sortedSongs.value) {
            if (playlistSongs.contains(song.song_id)) {
                _currentPlaylistSongs.value.add(song)
            }
        }
    }

    fun onAddSongToPlaylist(playlist_id : String, song_id: String) {
        _repository.addSongToPlaylist(playlist_id, _currentUser.value, song_id)
        onOpenPlaylist(playlist_id)
    }
    fun deleteSongFromPlaylist(
        playlist_id: String,
        user_id: String,
        song_id: String
    ) {
        _repository.deleteSongFromPlaylist(
            playlist_id,
            user_id,
            song_id
        )
        onOpenPlaylist(playlist_id)
    }

    fun onLikeSong(user_id : String, song : SongModel, isLiked : Boolean) {
        _repository.toggleLike(user_id, song, isLiked)
        _likedSongs.value = getLikedSongs(user_id)
    }

    fun setSortedSongs() {
        _sortedSongs.value = _repository.sortByLikes()
    }

    fun setTopTen() {
        _topTen.value = ArrayList()
        for (i in 0..9) {
            if (i < _sortedSongs.value.size) {
                _topTen.value.add(_sortedSongs.value[i])
            }
        }
    }

    fun addNewPlaylist(user_id: String, playlist_name: String) {
        _repository.addNewPlaylist(user_id, playlist_name)
        setUserPlaylists(user_id)
    }

    suspend fun addNewSong(
        id: String,
        title: String,
        artist: String,
        category: String,
        url: String
    ) {
        _repository.addSong(id, title, artist, category, url)
    }

    private fun createNotification(likes : Int): Notification {
        createNotificationChannel()

        val builder = NotificationCompat.Builder(getApplication<Application>().applicationContext, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.btn_star)
            .setContentTitle("Congratulations!")
            .setContentText("$likes of your likes made it to the top ten!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "MusicLadder Notification"
            val descriptionText = "Notification channel for MusicLadder"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getApplication<Application>().applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        val CHANNEL_ID = "com.example.finalproject.channel"
    }

}