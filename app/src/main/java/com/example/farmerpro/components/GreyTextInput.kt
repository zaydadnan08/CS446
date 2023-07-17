package com.example.farmerpro.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreyTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 16.sp)
            )
        },
        textStyle = TextStyle(fontSize = 16.sp, lineHeight = 32.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFFE5E5E5),
            focusedBorderColor = Color.Transparent,
            cursorColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .background(Color(0xFFE5E5E5), RoundedCornerShape(12.dp))
    )
}