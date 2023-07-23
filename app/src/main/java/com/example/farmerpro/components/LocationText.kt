package com.example.farmerpro.components

import android.content.Context
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
fun LocationText(location: String, context: Context){
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
    ClickableText(
        text = annotatedString,
        style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            color = Color(0xFF3A85FF)
        ),
        maxLines = 1,
        onClick = {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${Uri.encode(location)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            }
        },
        overflow = TextOverflow.Ellipsis
        )
}