package com.example.finalproject.ui.nav

sealed class Routes(val route: String) {
    object Landing : Routes("landing")
    object Browse : Routes("browse")
    object PlayList : Routes("playlist")
    object Category : Routes("category")
    object SignUp : Routes("signup")
    object LogIn : Routes("login")
}