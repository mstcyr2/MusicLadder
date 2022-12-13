package com.example.finalproject.ui.nav

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.finalproject.ui.*
import com.example.finalproject.ui.viewmodel.AppViewModel

/**
 * Navigation
 * Each composable listed as a screen, and will execute specific screen
 */
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Landing.route
    ) {
        composable(Routes.Landing.route) {
            vm.setSortedSongs()
            vm.setTopTen()
            LandingScreen(navController = navController, vm = vm)
            Log.d("App", "Navigated to the landing page")
        }

        composable(Routes.Browse.route) {
            BrowseScreen(navController = navController, vm = vm)
            vm.setSortedSongs()
            Log.d("App", "Navigated to the browse page")
        }

        composable(Routes.Category.route) {
            val category by vm.selectedCategory
            CategoryScreen(navController = navController, genre = category, vm = vm)

            Log.d("App", "Navigated to the category page")
        }

        composable(Routes.PlayList.route) {
            PlaylistScreen(
                navController = navController,
                vm
            )
            vm.setUserPlaylists(vm.currentUser.value)

            Log.d("App", "Navigated to the playlist page")
        }
        composable(Routes.SignUp.route) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                SignUpScreen(navController = navController, vm)
            }
        }
        composable(Routes.LogIn.route) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                LogInScreen(navController = navController, vm)
            }
        }
        composable(Routes.SongsInPlaylist.route) {
            PlaylistSongsScreen(navController = navController, vm = vm)
        }

    }

}