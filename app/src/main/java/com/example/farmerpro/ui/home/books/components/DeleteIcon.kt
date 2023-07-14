package com.example.farmerpro.ui.home.books.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable

@Composable
fun DeleteIcon(
    deleteBook: () -> Unit
) {
    IconButton(
        onClick = deleteBook
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "DELETE_BOOK",
        )
    }
}