package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.finalproject.ui.BrowseScreen
import com.example.finalproject.ui.CategoryScreen
import com.example.finalproject.ui.Greeting
import com.example.finalproject.ui.LandingScreen
import com.example.finalproject.ui.data.MyPlaylists
import com.example.finalproject.ui.theme.FinalProjectTheme
import com.example.finalproject.ui.PlaylistScreen
import com.example.finalproject.ui.nav.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinalProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FinalProjectTheme {
        Greeting("Android")
    }
}