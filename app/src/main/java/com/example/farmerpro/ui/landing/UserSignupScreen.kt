package com.example.farmerpro.ui.landing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.ui.basic.FarmerTextInput

@Composable
fun UserSignupScreen(navController: NavController) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.farmer),
            modifier = Modifier.size(64.dp),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(32.dp))

        FarmerTextInput(
            value = fullName,
            onValueChange = { fullName = it },
            placeholder = "Full Name"
        )

        Spacer(modifier = Modifier.height(16.dp))

        FarmerTextInput(
            value = email,
            onValueChange = { email = it },
            placeholder = "Email"
        )

        Spacer(modifier = Modifier.height(16.dp))

        FarmerTextInput(
            value = password,
            onValueChange = { password = it },
            placeholder = "Password"
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Transparent)
                .border(
                    BorderStroke(2.dp, Color.Black),
                    CircleShape
                ),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            onClick = {
                navController.navigate(Screens.Home.name);
            },

        ) {
            Text(text = "Sign Up", color = Color.Black)
        }
    }
}
