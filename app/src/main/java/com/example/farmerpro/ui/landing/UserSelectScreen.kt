package com.example.farmerpro.ui.landing

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens


@Composable
fun UserSelectScreen(navController: NavController){
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
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "who are you?", fontSize = 26.sp)
        Spacer(modifier = Modifier.height(64.dp))
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
                navController.navigate(Screens.UserSignup.name);
            },

        ) {
            Text(text = "customer", color = Color.Black)
        }
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
                navController.navigate(Screens.UserSignup.name);
            },

        ) {
            Text(text = "farmer", color = Color.Black)
        }
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
                navController.navigate(Screens.UserSignup.name);
            },

        ) {
            Text(text = "community fridge", color = Color.Black)
        }
    }
}