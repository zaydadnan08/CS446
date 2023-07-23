package com.example.farmerpro.ui.home.markets

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.ui.home.markets.components.AddItemAlertDialog
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.components.SearchAppBar
import com.example.farmerpro.components.ToggleWithText
import com.example.farmerpro.core.Constants
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.ui.home.markets.components.AddItem
import com.example.farmerpro.ui.home.markets.components.Items
import com.example.farmerpro.ui.home.markets.components.DeleteItem
import com.example.farmerpro.ui.home.markets.components.ItemCard
import com.example.farmerpro.ui.home.markets.components.ItemDialog

@Composable
fun ItemsScreen(
    viewModel: MarketViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(MarketplaceItem()) }
    var isChecked by remember { mutableStateOf(false) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                viewModel.addImageToStorage(imageUri)
            }
        }
    Scaffold(content = { padding ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
        ) {
            Text(
                text = "Marketplace", modifier = Modifier.padding(
                    bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp
                ), style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Start
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }
            SearchAppBar(searchQuery, setSearchQuery)
            Spacer(modifier = Modifier.height(8.dp))

            ToggleWithText(isChecked = isChecked, onCheckedChange = { isChecked = it })

            Items(itemsContent = { items ->
                var filteredItems = if (searchQuery.isNotEmpty()) {
                    items.filter { item ->
                        item.product_name.contains(searchQuery, ignoreCase = true)
                    }
                } else {
                    items
                }
                filteredItems = if (isChecked) {
                    filteredItems.filter { item ->
                        item.uid == viewModel.userId.value
                    }
                } else {
                    filteredItems
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), modifier = Modifier
                        .padding(1.dp)
                        .fillMaxSize()
                ) {
                    filteredItems.forEach { item ->
                        item {
                            ItemCard(item = item, onCardClick = {
                                selectedItem = it
                                showDialog = true
                            })
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
                if (openDialog) {
                    AddItemAlertDialog(closeDialog = {
                        openDialog = false
                    }, addItem = { product_name, price, description, location ->
                        viewModel.addItem(product_name, price, description, location)
                    }, openGallery = {
                        galleryLauncher.launch(Constants.ALL_IMAGES)
                    }, viewModel = viewModel
                    )
                }
                if (showDialog) {
                    ItemDialog(
                        closeDialog = { showDialog = false },
                        item = selectedItem,
                        owner = selectedItem.uid == viewModel.userId.value,
                        deleteItem = {
                            selectedItem.id?.let { itemId ->
                                viewModel.deleteItem(itemId)
                            }
                        },
                    )
                }
            })
        }
    }, floatingActionButton = {
        AddFloatingActionButton(openDialog = {
            openDialog = true
        })
    })
    AddItem()
    DeleteItem()
}



