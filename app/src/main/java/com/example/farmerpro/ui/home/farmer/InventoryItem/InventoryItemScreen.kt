package com.example.farmerpro.ui.home.farmer.InventoryItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.components.Dropdown
import com.example.farmerpro.ui.home.farmer.components.AddInventoryAlertDialog
import com.example.farmerpro.ui.home.farmer.components.TrackSaleDialog
import com.example.farmerpro.ui.home.farmer.farmViewModel

@Composable
fun InventoryItemScreen(viewModel: farmViewModel = hiltViewModel(), navController: NavController, name: String, quantity: Double, unit: String, notes: String) {
    var selectedUnit by remember { mutableStateOf(unit) }
    var selectedQuantity by remember { mutableStateOf(quantity) }
    var openDialog by remember { mutableStateOf(false) }

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
                        selectedQuantity = selectedQuantity.minus(1.0)
                    }) {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Remove",
                            tint = LocalContentColor.current
                        )
                    }
                    Text(
                        text = selectedQuantity.toString(),
                        style = MaterialTheme.typography.h6,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    IconButton(onClick = { selectedQuantity = selectedQuantity.plus(1.0) }) {
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

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
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

                Spacer(modifier = Modifier.width(16.dp)) // Add some space between the buttons

                Button(
                    onClick = {
                        viewModel.updateInventoryItem(name, selectedQuantity, selectedUnit)
                        navController.popBackStack()
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
            navController = navController,
            closeDialog = {
                openDialog = false
            },
            name = name,
            origQuantity = quantity
        )
    }
}