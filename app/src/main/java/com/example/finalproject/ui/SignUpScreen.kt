package com.example.finalproject.ui

import android.os.Message
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.*
import androidx.core.app.NotificationCompat
import androidx.navigation.NavController
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
    val exists = remember { mutableStateOf(false) }
    
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
                    exists.value = true
                }
            }
        }
        ) {
            Text("Sign Up!")
        }
        if (exists.value) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Filled.Warning, contentDescription = "warning")
                Text(text = "Oops! That account already exists.")
            }
        } else {
            Text(text = "")
        }

    }
}

