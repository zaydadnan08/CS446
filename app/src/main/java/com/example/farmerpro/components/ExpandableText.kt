package com.example.farmerpro.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("SuspiciousIndentation")
@Composable
fun ExpandableText(
    text: String,
    maxLines: Int = 1,
    color: Color = Color.Gray,
) {
    var expanded by remember { mutableStateOf(false) }
    Column (
        modifier = Modifier.padding(horizontal = 12.dp)
    ){
            Text(
                text = "Description: ",
                fontWeight = FontWeight.Bold,
                style = typography.body2.copy(fontSize = 18.sp),
                maxLines = if (expanded) Int.MAX_VALUE else maxLines,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = text,
                style = typography.body2.copy(fontSize = 16.sp),
                maxLines = if (expanded) Int.MAX_VALUE else maxLines,
                color = color,
                overflow = TextOverflow.Ellipsis
            )
        if(text.length > 25)
        TextButton(onClick = { expanded = !expanded }) {
            Text(
                text = if (expanded) "View Less" else "View More",
                color = color,
                style = typography.button.copy(fontSize = 12.sp),
                modifier = Modifier.padding(0.dp)
            )
        }

    }
}
