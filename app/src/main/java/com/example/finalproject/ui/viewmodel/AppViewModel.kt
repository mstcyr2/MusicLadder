package com.example.finalproject.ui.viewmodel

import android.app.Application
import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.database.models.SongModel
import com.example.finalproject.database.repo.AppRepository
import com.example.finalproject.database.repo.IRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.lang.NullPointerException

class AppViewModel(app : Application) : AndroidViewModel(app) {

    private val _currentUser =  mutableStateOf("")
    val currentUser : State<String> = _currentUser

    private val _likedSongs = mutableStateOf(ArrayList<String>())
    val likedSongs = _likedSongs

    private val _userPlaylists = mutableStateOf(ArrayList<String>())
    val userPlaylists = _userPlaylists

    private val _selectedCategory = mutableStateOf("")
    val selectedCategory = _selectedCategory

    private val _sortedSongs = mutableStateOf(ArrayList<SongModel>())
    val sortedSongs = _sortedSongs

    private val _filteredSongs = mutableStateOf(ArrayList<SongModel>())
    val filteredSongs = _filteredSongs

    private lateinit var _repository : IRepository

    init {
        viewModelScope.launch {
            _repository = AppRepository(getApplication())
            _sortedSongs.value = _repository.sortByLikes()
        }
    }

    private fun onSetCurrentUser(user_id: String) {
        if (user_id != "") {
            _currentUser.value = user_id
            _likedSongs.value = getLikedSongs(user_id)
        } else
            _currentUser.value = user_id
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