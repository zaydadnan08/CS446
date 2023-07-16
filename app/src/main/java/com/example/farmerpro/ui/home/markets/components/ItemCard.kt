package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.domain.model.MarketplaceItem

@Composable
fun ItemCard(
    item: MarketplaceItem,
    deleteItem: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = 3.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = item.product_name.orEmpty(),
                    color = Color.DarkGray,
                    fontSize = 25.sp
                )
                Text(
                    text = "by ${item.seller.orEmpty()}",
                    color = Color.DarkGray,
                    fontSize = 12.sp,
                    textDecoration = TextDecoration.Underline
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = deleteItem
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "DELETE_ITEM",
                )
            }

        }
    }
}