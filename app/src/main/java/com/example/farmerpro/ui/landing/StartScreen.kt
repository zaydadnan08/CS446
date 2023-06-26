package com.example.farmerpro.ui.landing

import androidx.compose.foundation.layout.*
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
import com.example.farmerpro.Screens
import com.example.farmerpro.R

@Composable
fun StartScreen(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.farmer),
            modifier = Modifier.size(128.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "farmer pro", fontSize = 32.sp)
        Text(text = "farming at its best.", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(128.dp))
        Button(
            onClick = {
                navController.navigate(Screens.UserSelect.name);
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text(text = "get started", color = Color.Black)
        }
    }
}

