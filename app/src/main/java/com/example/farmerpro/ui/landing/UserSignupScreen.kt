package com.example.farmerpro.ui.landing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens

@Composable
fun UserSignupScreen(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.farmer),
            modifier = Modifier.size(64.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(64.dp))
        TextField(value = "",
            onValueChange = {},
            label = { Text("Full name") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "",
            onValueChange = {},
            label = { Text("Email") })
        Spacer(modifier = Modifier.height(8.dp))
        TextField(value = "",
            onValueChange = {},
            label = { Text("Password") })
        Spacer(modifier = Modifier.height(56.dp))
        Button(
            onClick = {
                navController.navigate(Screens.Home.name);
            },
        ) {
            Text(text = "sign up")
        }
    }
}