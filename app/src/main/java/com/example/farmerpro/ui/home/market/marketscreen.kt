package com.example.farmerpro.ui.home.market

import Item
import MarketplaceItemCard
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.R

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun MarketScreen(){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
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
            Item("Apple", "$2.99/lb", "Sweet and crispy", R.drawable.apple),
            Item("Carrot", "$1.49/lb", "Crunchy and nutritious", R.drawable.carrot),
            Item("Blueberry", "$3.99/pint", "Juicy and antioxidant-rich", R.drawable.blueberry),
            Item("Banana", "$0.49/each", "Naturally sweet and energy-boosting", R.drawable.banana),
            Item("Onion", "$0.99/lb", "Adds flavor to various dishes", R.drawable.onion),
            Item("Potato", "$1.99/lb", "Versatile and filling", R.drawable.potato),
            Item("Strawberry", "$4.99/pint", "Sweet and refreshing", R.drawable.strawberry),
            Item("Orange", "$0.79/each", "Citrus fruit with tangy flavor", R.drawable.orange)
        )

        LazyVerticalGrid(cells = GridCells.Fixed(2)) {
            items.forEach { item ->
                item {
                    MarketplaceItemCard(item = item)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}