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

class AppViewModel(app : Application) : AndroidViewModel(app) {

    val ctx = LocalContext

    private val _currentUser =  mutableStateOf("")
    val currentUser : State<String> = _currentUser

    private lateinit var _repository : IRepository

    init {
        viewModelScope.launch {
            _repository = AppRepository(getApplication())
        }

    }

    private var selectedCategory: String = ""

    fun setSelectedCategory(category: String) {
        selectedCategory = category
    }

    fun getSelectedCategory(): String {
        return selectedCategory
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

    fun onLogIn(user : String, pass : String) {

    }

    fun onLikeSong(user_id : String, song : SongModel) {

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