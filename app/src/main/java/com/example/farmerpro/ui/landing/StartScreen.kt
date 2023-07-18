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
import com.example.farmerpro.Screens
import com.example.farmerpro.R
import com.example.farmerpro.components.BorderedButton

@Composable
fun StartScreen(navController: NavController) {
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
        BorderedButton(value = "get started", onClick = {
            navController.navigate(Screens.UserSelect.name);
        })
    }
}

