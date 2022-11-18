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
import com.example.finalproject.ui.theme.FinalProjectTheme

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
                    // TODO: Add NavController for swapping between screens
                    // TODO: NavController functionality for browse top 50 cards
                    // For now, just swap boolean 'screen' val below

                    val screenNumber = 1

                    when (screenNumber) {
                        1 -> {
                            LandingScreen()
                        }

                        2 -> {
                            BrowseScreen()
                        }

                        3 -> {
                            CategoryScreen("Top 50 Rock")
                        }
                    }
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