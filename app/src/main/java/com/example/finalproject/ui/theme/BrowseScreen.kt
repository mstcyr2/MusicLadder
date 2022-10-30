package com.example.finalproject.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Main function for launching browsing page
 */
@Preview
@Composable
fun BrowseScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Text("Browse Categories", fontSize = 32.sp)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Buttons()
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)) {
        CategoryOrganization()
    }
}

/**
 * Defines the card elements for each category, organizes into fixed 2 count grid
 * Adding further category names can be done below in 'categoryNames'
 * TODO: Add functionality for pairing with ICON & CATEGORY NAME
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryOrganization() {
    val categoryNames = listOf("Rock", "Hip Hop", "Pop", "Jazz", "Country", "Blues", "Metal",
        "Electronic", "Classical", "Punk", "Soul", "Alternative", "Folk", "Latin", "Funk", "Reggae",
        "Techno", "Indie", "House", "Disco", "Instrumental", "New Wave")

    LazyVerticalGrid(
        modifier = Modifier.padding(top = 144.dp),
        horizontalArrangement = Arrangement.Center,
        cells = GridCells.Fixed(2),
        content = {
            items(categoryNames.size) { index : Int ->
                Row(
                    modifier = Modifier.padding(end = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    BrowseCard(icon = "ICON", category = categoryNames[index])
                }
            }
        })
}

/**
 * Creates card visually for LazyVerticalGrid organization
 */
@Composable
fun BrowseCard(icon: String, category: String) {
    val configuration = LocalConfiguration.current
    var cardWidth = configuration.screenWidthDp.dp

    cardWidth -= (cardWidth / 2)

    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(5.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .width(cardWidth)
                .padding(all = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(icon, color = Color.Magenta)
            }
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                Text(category, color = Color.Magenta)
            }
        }
    }
}

/**
 * Modified reused function from LandingScreen.kt to modify buttons for UI interfacing
 */
@Composable
fun Buttons() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 64.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Home", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = {},
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}