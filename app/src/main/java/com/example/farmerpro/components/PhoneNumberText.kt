package com.example.farmerpro.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import android.content.Intent
import android.net.Uri
import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun PhoneNumberText(phoneNumber: String) {
    val annotatedString = buildAnnotatedString {
        withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
            append(formatPhoneNumber(phoneNumber))
        }
        addStringAnnotation(
            tag = "PHONE",
            annotation = phoneNumber,
            start = 0,
            end = phoneNumber.length
        )
    }

    val context = LocalContext.current

    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(tag = "PHONE", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:${annotation.item}")
                    context.startActivity(dialIntent)
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

fun formatPhoneNumber(phoneNumber: String): String {
    return "${phoneNumber.substring(0, 3)}-${phoneNumber.substring(3, 6)}-${phoneNumber.substring(6)}"
}
