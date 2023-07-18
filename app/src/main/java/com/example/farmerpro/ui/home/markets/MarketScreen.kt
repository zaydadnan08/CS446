package com.example.farmerpro.ui.home.markets

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
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
import com.example.farmerpro.core.Constants
import com.example.farmerpro.ui.home.markets.components.AddItem
import com.example.farmerpro.ui.home.markets.components.Items
import com.example.farmerpro.ui.home.markets.components.ItemsContent
import com.example.farmerpro.ui.home.markets.components.DeleteItem
import kotlinx.coroutines.launch

@Composable
fun ItemsScreen(
    viewModel: MarketViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val galleryLauncher =  rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
        imageUri?.let {
            viewModel.addImageToStorage(imageUri)
        }
    }
        Scaffold(
        content = { padding ->
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = "Marketplace",
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

                Items(
                    itemsContent = { items ->
                        val filteredItems = if (searchQuery.isNotEmpty()) {
                            items.filter { item ->
                                item.product_name.contains(searchQuery, ignoreCase = true)
                            }
                        } else {
                            items
                        }
                        ItemsContent(
                            items = filteredItems,
                            deleteItem = { itemId ->
                                viewModel.deleteItem(itemId)
                            }
                        )
                        if (openDialog) {
                            AddItemAlertDialog(
                                closeDialog = {
                                    openDialog = false
                                },
                                addItem = { product_name, price, description, location ->
                                    viewModel.addItem(product_name, price, description, location)
                                },
                                openGallery = {
                                    galleryLauncher.launch(Constants.ALL_IMAGES)
                                },
                                viewModel = viewModel
                            )
                        }
                    }
                )

            }
        },
        floatingActionButton = {
            AddFloatingActionButton(
                openDialog = {
                    openDialog = true
                }
            )
        }
    )
    AddItem()
    DeleteItem()
}



