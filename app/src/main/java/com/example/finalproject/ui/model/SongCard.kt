package com.example.finalproject.ui.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SongCard(rank: Int, title: String, artist: String, liked: MutableState<Boolean>) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 4.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.width(32.dp)) {
                Text(rank.toString(), color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(title, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(120.dp)) {
                Text(artist, color = Color.Magenta)
            }
            Column(modifier = Modifier.width(32.dp)) {
                IconButton(onClick = { liked.value = !liked.value }) {
                    Icon(
                        imageVector = if (liked.value) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = Color.Magenta
                    )
                }
            }
        }
    }
}