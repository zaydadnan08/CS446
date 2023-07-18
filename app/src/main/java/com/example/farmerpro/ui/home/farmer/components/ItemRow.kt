package com.example.farmerpro.ui.home.farmer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.ui.home.farmer.farmViewModel
import com.example.farmerpro.ui.theme.Gray400

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemRow(
    item: InventoryItem,
    viewModel: farmViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(
                BorderStroke(0.5.dp, Gray400),
                RoundedCornerShape(16.dp)
            ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // This will move quantity to the right
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.h6,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp) // Spacing between buttons and quantity
            ) {
                IconButton(onClick = { viewModel.decrementInventoryCount(item.name) }) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Remove",
                        tint = LocalContentColor.current
                    )
                }
                Text(
                    text = item.quantity.toString(),
                    style = MaterialTheme.typography.h6,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(onClick = { viewModel.incrementInventoryCount(item.name) }) {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Add",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { viewModel.deleteItem(item.name)}) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = LocalContentColor.current
                    )
                }

            }
        }
    }
}