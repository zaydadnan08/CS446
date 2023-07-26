package com.example.farmerpro.ui.home.farmer.InventoryItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.components.Dropdown
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import com.example.farmerpro.ui.home.bottomBar.FarmerSubScreens
import com.example.farmerpro.ui.home.farmer.components.AddInventoryAlertDialog
import com.example.farmerpro.ui.home.farmer.components.SalesRow
import com.example.farmerpro.ui.home.farmer.components.TrackSaleDialog
import com.example.farmerpro.ui.home.farmer.farmViewModel

@Composable
fun InventoryItemScreen(viewModel: farmViewModel = hiltViewModel(), navController: NavController, name: String, quantity: Double, unit: String, notes: String) {
    var selectedUnit by remember { mutableStateOf(unit) }
    var selectedQuantity by remember { mutableStateOf(quantity.toString()) }
    var openDialog by remember { mutableStateOf(false) }
    var sales: List<SaleRecord> = when(val salesResponse = viewModel.salesResponse) {
        is Response.Success -> salesResponse.data.sales.filter { it.name == name }
        else -> {
            emptyList<SaleRecord>()
        }
    }

    val myChangeQuantity: quantityFun = object : quantityFun {
        override fun changeQuantity(quantity: String) {
            selectedQuantity = (selectedQuantity.toDouble() - quantity.toDouble()).toString()
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 32.dp),
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Black
                )
            )
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                    tint = LocalContentColor.current
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = notes,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
            color = Color.Gray
        )

        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 36.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Quantity",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    IconButton(onClick = {
                        selectedQuantity = if (selectedQuantity.toDouble().minus(1.0) >= 0.0) (selectedQuantity.toDouble().minus(1.0)).toString() else "0.0"
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Remove",
                            tint = LocalContentColor.current
                        )
                    }
                    TextField(
                        value = selectedQuantity,
                        onValueChange = { selectedQuantity = it },
                        textStyle = MaterialTheme.typography.h6,
                        maxLines = 1,
                        modifier = Modifier
                            .width(90.dp) // Set the width of the TextField
                            .padding(horizontal = 2.dp), // Add horizontal padding for better spacing
                        singleLine = true,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                val newQuantity = selectedQuantity.toDoubleOrNull() ?: 0.0
                                viewModel.updateInventoryItem(
                                    name,
                                    newQuantity,
                                    unit,
                                    notes
                                )
                            }
                        )
                    )
                    IconButton(onClick = { selectedQuantity = selectedQuantity.toDouble().plus(1.0).toString() }) {
                        Icon(
                            Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Add",
                            tint = LocalContentColor.current
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(32.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Unit",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Dropdown(
                    options = listOf("lbs", "kg", "cnt"),
                    onOptionSelected = { selectedUnit = it },
                    selectedOption = selectedUnit
                )
            }
        }

        Text(
            text = "Sales History",
            modifier = Modifier.padding(
                bottom = 8.dp, top = 12.dp, end = 4.dp
            ),
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Start
            )
        )
        if (sales.isEmpty()) {
            Text(text = "No Sales")
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .padding(1.dp)
                .fillMaxHeight(0.7f)
        ) {
            sales.forEach { item ->
                item {
                    SalesRow(
                        item = item,
                        viewModel = viewModel,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = {
                    viewModel.deleteItem(name)
                    navController.popBackStack()
                }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete",
                        tint = Color.Red
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(onClick = {
                    openDialog = true
                }) {
                    Text(
                        text = "Track a Sale",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    )
                }

                Spacer(modifier = Modifier.width(24.dp))

                Button(
                    onClick = {
                        viewModel.updateInventoryItem(name, selectedQuantity.toDouble(), selectedUnit)
                        navController.navigate("FarmerHome")
                    }
                ) {
                    Text(
                        text = "Save",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
    if (openDialog) {
        TrackSaleDialog(
            onSave = myChangeQuantity,
            closeDialog = {
                openDialog = false
            },
            name = name,
            origQuantity = quantity,
            unit = unit
        )
    }
}

interface quantityFun {
    fun changeQuantity(quantity: String)
}