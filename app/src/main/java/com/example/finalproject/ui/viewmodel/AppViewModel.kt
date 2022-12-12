package com.example.finalproject.ui.viewmodel

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.database.sqlite.SQLiteException
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.database.repo.AppRepository
import com.example.finalproject.database.repo.IRepository
import com.example.finalproject.ui.data.MyPlaylists
import com.example.finalproject.ui.data.Playlist
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class AppViewModel(app : Application) : AndroidViewModel(app) {

    private val _currentUser =  mutableStateOf("")
    val currentUser : State<String> = _currentUser

    private val _likedSongs = mutableStateOf(ArrayList<String>())
    val likedSongs = _likedSongs

    private val likesInTT = mutableStateOf(0) //the number of the users liked songs in the top ten

    private val _userPlaylists: MutableState<List<Playlist>> =  mutableStateOf(MyPlaylists)
    val userPlaylists = _userPlaylists

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
            _sortedSongs.value = _repository.sortByLikes()
            for (i in 0..9) {
                if (i < _sortedSongs.value.size) {
                    _topTen.value.add(_sortedSongs.value[i])
                }
            }

        }
    }

    private fun onSetCurrentUser(user_id: String) {
        if (user_id != "") {
            _currentUser.value = user_id
            _likedSongs.value = getLikedSongs(user_id)
            for (song in _topTen.value) {
                if (_likedSongs.value.contains(song.song_id)) {
                    likesInTT.value += 1
                }
            }
            val notif = createNotification(likesInTT.value)
            NotificationManagerCompat.from(getApplication<Application>().applicationContext)
                .notify(likesInTT.hashCode(), notif)
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
    }

    private fun getLikedSongs(user_id: String) : ArrayList<String> {
        return _repository.getUserLikes(user_id)
    }

    fun onLikeSong(user_id : String, song : SongModel, isLiked : Boolean) {
        _repository.toggleLike(user_id, song, isLiked)
        _likedSongs.value = getLikedSongs(user_id)
    }

    fun onSearch(search: String) {
        _userPlaylists.value =
            userPlaylists
                .value
                .filter { p -> p
                    .title
                    .contains(search, true)
                }
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

//    private val _fetchedSongs: MutableState<List<SongModel>> = mutableStateOf(listOf())
//    val fetchedSongs: State<List<SongModel>> = _fetchedSongs
//
//    private lateinit var _repository: SongsRepository
//    private val songsDatabase: SongInterface = DatabaseHandler(context)
//
//    init {
//        viewModelScope.launch {
//            val fetchedSongs = songsDatabase.readSongs()
//            _repository = SongMemoryRepository(fetchedSongs)
//            _fetchedSongs.value = _repository.readSongs()
//        }
//    }
}