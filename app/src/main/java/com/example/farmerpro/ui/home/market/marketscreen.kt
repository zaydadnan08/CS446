package com.example.farmerpro.ui.home.market

import Item
import MarketplaceItemList
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun MarketScreen(){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "Marketplace",
            modifier = Modifier.padding(bottom = 8.dp, top = 12.dp,
                start = 8.dp, end=4.dp), style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        val items = listOf(
            Item("Item 1", "$10", "Description 1", 1),
            Item("Item 2", "$15", "Description 2", 2),
            Item("Item 3", "$20", "Description 3", 3),
            Item("Item 4", "$25", "Description 4", 4)
        )

        MarketplaceItemList(items = items)
    }
}