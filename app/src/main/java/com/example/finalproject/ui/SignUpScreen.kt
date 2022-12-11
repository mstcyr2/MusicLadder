package com.example.finalproject.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.finalproject.R
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    vm: AppViewModel
) {
    val username = remember { mutableStateOf( "" ) }
    val password = remember { mutableStateOf("") }
    val verifier = remember { mutableStateOf("") }
    val ctx = LocalContext.current
    
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(500.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "logo",
            modifier = Modifier
                .clip(CircleShape)
                .size(120.dp)
        )
        Text(text = "Sign Up", fontSize = 32.sp)
        TextField(
            value = username.value,
            onValueChange = { username.value = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "Username") },
            placeholder = {
                Text(text = "Username")
            }
        )
        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Password") },
            placeholder = {
                Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                IconToggleButton(checked = false, onCheckedChange = {}) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Toggle Show Password")
                }
            }
        )
        TextField(
            value = verifier.value,
            onValueChange = { verifier.value = it },
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = "Verify Password") },
            placeholder = {
                Text(text = "Verify Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = {
                IconToggleButton(checked = false, onCheckedChange = {}) {
                    Icon(imageVector = Icons.Filled.Info, contentDescription = "Toggle Show Password")
                }
            }
        )
        Button(onClick = { 
            if (password.value == verifier.value) {
                val isNew = vm.onSignUp(username.value, password.value)
                if (isNew) {
                    navController.navigate(Routes.Landing.route)
                } else {
                    Toast.makeText(ctx, "Oops! That account already exists.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(ctx, "Passwords do not match! Try again.", Toast.LENGTH_LONG).show()
            }
        }
        ) {
            Text("Sign Up!")
        }
        Text(text = "Already have an account? Log In here!", modifier = Modifier.clickable {
            navController.navigate(Routes.LogIn.route)
        })

    }
}

