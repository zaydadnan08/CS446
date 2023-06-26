package com.example.farmerpro.ui.home.farmer

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FarmScreen(){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Farmer",
            modifier = Modifier.padding(bottom = 8.dp, top = 12.dp,
                start = 8.dp, end=4.dp), style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}