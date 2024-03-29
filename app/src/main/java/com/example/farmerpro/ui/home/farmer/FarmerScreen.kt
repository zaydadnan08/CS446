package com.example.farmerpro.ui.home.farmer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.components.SearchAppBar
import com.example.farmerpro.components.Title
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.ui.home.bottomBar.FarmerSubScreens
import com.example.farmerpro.ui.home.bottomBar.navigation
import com.example.farmerpro.ui.home.farmer.components.AddInventoryAlertDialog
import com.example.farmerpro.ui.home.farmer.components.ItemRow

@Composable
fun FarmerHomeScreen (
    viewModel: farmViewModel = hiltViewModel(),
    navController: NavController,
    mainNavController: NavController,
    userType: String
) {
    var openDialog by remember { mutableStateOf(false) }

    var items: InventoryItems = when(val itemsResponse = viewModel.inventoryResponse) {
        is Response.Success -> itemsResponse.data
        else -> {
            InventoryItems(emptyList<InventoryItem>())
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Title(text = "Farmer", onClick = {
                        viewModel.signOut()
                        mainNavController.navigate(Screens.Start.name)
                    })
                    Button(
                        onClick = { navController.navigate(FarmerSubScreens.Analytics.name) },
                    ) {
                        Image(
                            modifier = Modifier.width(35.dp),
                            painter = painterResource(id = R.drawable.analytics_icon),
                            contentDescription = "Analytics",
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }
                SearchAppBar(searchQuery, setSearchQuery)
                Spacer(modifier = Modifier.height(8.dp))
                if (items.inventory.isEmpty()) {
                    Text(text = "No inventory. Please add inventory by clicking on the plus icon")
                }
                LazyVerticalGrid(
                    columns= GridCells.Fixed(1),
                    modifier = Modifier
                        .padding(1.dp)
                        .fillMaxSize()) {
                        if (searchQuery.isNotEmpty()) {
                            items.inventory.filter { item ->
                                item.name.contains(searchQuery, ignoreCase = true)
                            }
                        } else {
                            items.inventory
                        }.forEach { item ->
                            item {
                                ItemRow(
                                    item = item,
                                    viewModel = viewModel,
                                    onClick = {
                                        navController.navigate("${FarmerSubScreens.InventoryItem.name}?name=${item.name}&quantity=${item.quantity}&unit=${item.unit}&notes=${item.notes}");
                                    }
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                    }
                }
                if (openDialog) {
                    AddInventoryAlertDialog(
                        closeDialog = {
                            openDialog = false
                        },
                        addItem = { name, quantity, unit, notes ->
                            viewModel.addItem(name, quantity, unit, notes)
                        }
                    )
                }
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
}