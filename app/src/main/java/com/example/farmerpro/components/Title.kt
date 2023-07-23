package com.example.farmerpro.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Title(text: String, onClick: () -> Unit = {}){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(
                bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp
            ),
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Start
            )
        )
        IconButton(onClick = {
            onClick()
        }) {
            Icon(
                imageVector = Icons.Default.ExitToApp, // Sign out icon
                contentDescription = "Sign Out",
                tint = Color.Black // Change the color if needed
            )
        }
    }
}