package com.example.farmerpro.ui.home.markets

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.ui.home.markets.components.AddItemAlertDialog
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.ui.home.markets.components.AddItem
import com.example.farmerpro.ui.home.markets.components.Items
import com.example.farmerpro.ui.home.markets.components.ItemsContent
import com.example.farmerpro.ui.home.markets.components.DeleteItem

@Composable
fun ItemsScreen(
    viewModel: MarketViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        content = { padding ->
            Items(
                itemsContent = { items ->
                    ItemsContent(
                        padding = padding,
                        items = items,
                        deleteItem = { itemId ->
                            viewModel.deleteItem(itemId)
                        }
                    )
                    if (openDialog) {
                        AddItemAlertDialog(
                            closeDialog = {
                                openDialog = false
                            },
                            addItem = { product_name, seller ->
                                viewModel.addItem(product_name, seller)
                            }
                        )
                    }
                }
            )
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