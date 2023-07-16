package com.example.farmerpro.ui.landing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.components.BorderedButton


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
        BorderedButton(
            value="customer",
            onClick = {
                navController.navigate(Screens.UserSignupCustomer.name);
            }
        )
        BorderedButton(
            value="farmer",
            onClick = {
                navController.navigate(Screens.UserSignupFarmer.name);
            }
        )
        BorderedButton(
            value="community fridge",
            onClick = {
                navController.navigate(Screens.UserSignupAdmin.name);
            }
        )
    }
}