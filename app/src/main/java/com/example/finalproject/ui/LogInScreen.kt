package com.example.finalproject.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.R
import com.example.finalproject.ui.nav.Routes
import com.example.finalproject.ui.viewmodel.AppViewModel

@Composable
fun LogInScreen(
    navController: NavHostController,
    vm: AppViewModel
) {
    val username = remember { mutableStateOf( "" ) }
    val password = remember { mutableStateOf("") }
    val exists = remember { mutableStateOf(false) }
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
        Text(text = "Log In", fontSize = 32.sp)
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(onClick = {
            exists.value = vm.onLogIn(username.value, password.value)
            if (exists.value) {
                navController.navigate(Routes.Landing.route)
            } else {
                Toast.makeText(ctx, "This account does not exist. Try again!", Toast.LENGTH_LONG).show()
            }
        }
        ) {
            Text("Log In!")
        }
        Text(text = "Don't have an account? Sign Up here!", modifier = Modifier.clickable {
            navController.navigate(Routes.SignUp.route)
        })

    }
}

