package com.example.farmerpro.ui.home.farmer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.components.SearchAppBar
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.ui.home.farmer.components.ItemRow
import com.example.farmerpro.ui.home.markets.MarketViewModel
import com.example.farmerpro.ui.home.markets.components.AddItem
import com.example.farmerpro.ui.home.markets.components.AddItemAlertDialog
import com.example.farmerpro.ui.home.markets.components.DeleteItem
import com.example.farmerpro.ui.home.markets.components.ItemCard
import com.example.farmerpro.ui.home.markets.components.Items
import com.example.farmerpro.ui.home.markets.components.ItemsContent

@Composable
fun FarmerScreen (
) {
    val items = InventoryItems(
        arrayOf(
            InventoryItem("Apples", 12.3),
            InventoryItem("Pears", 11),
            InventoryItem("Oranges", 1.2),
            InventoryItem("Grapes", 4),
            InventoryItem("Watermelon", 6.8),))

    Scaffold(
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = "Farmer",
                    modifier = Modifier.padding(bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Start
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }
                SearchAppBar(searchQuery, setSearchQuery)
                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalGrid(
                    columns= GridCells.Fixed(1),
                    modifier = Modifier.padding(1.dp).fillMaxSize()) {
                    items.inventoryItems.forEach { item ->
                        item {
                            ItemRow(
                                item = item,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }

            }
        },
        floatingActionButton = {
            AddFloatingActionButton(
                openDialog = {
                }
            )
        }
    )
}