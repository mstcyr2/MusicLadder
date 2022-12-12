package com.example.finalproject.ui.nav

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.finalproject.ui.*
import com.example.finalproject.ui.viewmodel.AppViewModel

/**
 * Navigation
 * Each composable listed as a screen, and will execute specific screen
 * TODO: Genre specific interfacing for the top 50 list (right now just "Rock Top 50")
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val vm: AppViewModel = viewModel()
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
            val category by vm.selectedCategory
            CategoryScreen(navController = navController, genre = category, vm = vm)

            Log.d("App", "Navigated to the category page")
        }

        composable(Routes.PlayList.route) {
            PlaylistScreen(navController = navController, vm)

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

    }
}

@ExperimentalFoundationApi
@Composable
fun PlaylistScreen(
    navController: NavHostController,
    vm: AppViewModel
) {
    val userPlaylists by vm.userPlaylists
    PlaylistsView(
        navController,
        onSearch=vm::onSearch,
        userPlaylists
    )
}