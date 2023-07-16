package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.farmerpro.domain.repository.Items

@Composable
fun ItemsContent(
    items: Items,
    deleteItem: (itemId: String) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        this.items(
            items = items
        ) { item ->
            ItemCard(
                item = item,
                deleteItem = {
                    item.id?.let { itemId ->
                        deleteItem(itemId)
                    }
                }
            )
        }
    }
}