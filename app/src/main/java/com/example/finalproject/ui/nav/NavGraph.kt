package com.example.finalproject.ui.nav

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.BrowseScreen
import com.example.finalproject.ui.CategoryScreen
import com.example.finalproject.ui.LandingScreen
import com.example.finalproject.ui.PlaylistScreen
import com.example.finalproject.ui.data.MyPlaylists
import com.example.finalproject.ui.viewmodel.SongViewModel

/**
 * Navigation
 * Each composable listed as a screen, and will execute specific screen
 * TODO: Genre specific interfacing for the top 50 list (right now just "Rock Top 50")
 */
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val vm: SongViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Landing.route
    ) {
        composable(Routes.Landing.route) {
            LandingScreen(navController = navController, vm = vm)

            Log.d("App", "Navigated to the landing page")
        }

        composable(Routes.Browse.route) {
            BrowseScreen(navController = navController, vm = vm)

            Log.d("App", "Navigated to the browse page")
        }

        composable(Routes.Category.route) {
            CategoryScreen(navController = navController, genre = vm.getSelectedCategory(), vm = vm)

            Log.d("App", "Navigated to the category page")
        }

        composable(Routes.PlayList.route) {
            PlaylistScreen(
                navController = navController,
                list = remember { mutableStateOf(MyPlaylists) }
            )

            Log.d("App", "Navigated to the playlist page")
        }
    }

}