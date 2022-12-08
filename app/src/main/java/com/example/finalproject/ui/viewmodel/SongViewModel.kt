package com.example.finalproject.ui.viewmodel

import androidx.lifecycle.ViewModel

class SongViewModel : ViewModel() {
    private var selectedCategory: String = ""

    fun setSelectedCategory(category: String) {
        selectedCategory = category
    }

    fun getSelectedCategory(): String {
        return selectedCategory
    }

    private var currentUserId: String = ""

    fun setCurrentUserId(category: String) {
        currentUserId = category
    }

    fun getCurrentUserId(): String {
        return currentUserId
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