package com.example.finalproject.ui.viewmodel

import com.example.finalproject.database.models.SongModel

interface SongsRepository {
    fun readSongs(): ArrayList<SongModel>
}