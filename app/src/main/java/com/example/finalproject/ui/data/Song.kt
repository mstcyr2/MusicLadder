package com.example.finalproject.ui.data

import androidx.compose.runtime.MutableState

data class Song (
    val rank: Int,
    val title: String,
    val artist: String,
    val liked: MutableState<Boolean>
)