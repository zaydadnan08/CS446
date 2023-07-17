package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.farmerpro.domain.repository.Items

@Composable
fun ItemsContent(
    items: Items,
    deleteItem: (itemId: String) -> Unit) {
        LazyVerticalGrid(
            columns= GridCells.Fixed(2),
            modifier = Modifier.padding(1.dp).fillMaxSize()) {
            items.forEach { item ->
                item {
                    ItemCard(
                        item = item,
                        deleteItem = {
                            item.id?.let { itemId ->
                                deleteItem(itemId)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }