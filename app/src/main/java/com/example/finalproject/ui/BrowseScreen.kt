package com.example.finalproject.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.theme.FinalProjectTheme

/**
 * Main function for launching browsing page
 */
@Composable
fun BrowseScreen(navController : NavHostController) {
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
        Buttons(navController = navController)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)) {
        CategoryOrganization(navController = navController)
    }
}

/**
 * Defines the card elements for each category, organizes into fixed 2 count grid
 * Adding further category names can be done below in 'categoryNames'
 * TODO: Add functionality for pairing with ICON & CATEGORY NAME
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryOrganization(navController: NavHostController) {
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
                    BrowseCard(navController = navController, icon = "ICON", category = categoryNames[index])
                }
            }
        })
}

/**
 * Creates card visually for LazyVerticalGrid organization
 */
@Composable
fun BrowseCard(
    navController: NavHostController,
    icon: String,
    category: String
) {
    val configuration = LocalConfiguration.current
    var cardWidth = configuration.screenWidthDp.dp

    cardWidth -= (cardWidth / 2)

    Card(
        modifier = Modifier
            .height(100.dp)
            .padding(bottom = 16.dp)
            .clickable { navController.navigate(Routes.Category.route) },
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
fun Buttons(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 62.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 8.dp), onClick = { navController.navigate(Routes.Landing.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Home", color = Color.White)
        }
        Button(modifier = Modifier
            .size(width = 110.dp, height = 48.dp)
            .padding(end = 4.dp), onClick = { navController.navigate(Routes.PlayList.route) },
            colors = ButtonDefaults.buttonColors(Color.Magenta)
        ) {
            Text(text = "Playlists", color = Color.White)
        }
    }
}

//@ExperimentalComposeUiApi
//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    FinalProjectTheme {
//        val vm = BrowseScreenViewModel()
//        BrowseScreen(vm)
//    }
//}