package com.example.farmerpro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GreyTextInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    maxLines: Int = 1
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        singleLine = placeholder != "Product Description",
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 16.sp)
            )
        },
        textStyle = TextStyle(fontSize = 16.sp, lineHeight = 24.sp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color(0xFFE5E5E5),
            focusedBorderColor = Color.Transparent,
            cursorColor = Color.Gray
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .background(Color(0xFFE5E5E5), RoundedCornerShape(12.dp))
            .heightIn(min = if (maxLines > 1) 72.dp else 32.dp)
    )
}
