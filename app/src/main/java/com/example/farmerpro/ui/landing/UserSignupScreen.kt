package com.example.farmerpro.ui.landing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farmerpro.Screens

@Composable
fun UserSignupScreen(navController: NavController){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            navController.navigate(Screens.Home.name);
        },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "click here for home navigation")
        }
    }
}