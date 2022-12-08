package com.example.finalproject.ui.viewmodel

import com.example.finalproject.database.models.SongModel

class SongMemoryRepository(private var _fetchedSongs: ArrayList<SongModel>) : SongsRepository {
    override fun readSongs(): ArrayList<SongModel> {
        return _fetchedSongs
    }
}