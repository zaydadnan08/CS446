package com.example.farmerpro.components

import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun LocationText(location: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append(location)
        }
        addStringAnnotation(
            tag = "PHONE",
            annotation = location,
            start = 0,
            end = location.length
        )
    }

    val context = LocalContext.current

    ClickableText(
        text = annotatedString,
        onClick = {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(location)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }
        },
        style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color(0xFF3A85FF)
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

