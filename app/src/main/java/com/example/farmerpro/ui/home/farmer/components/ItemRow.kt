package com.example.farmerpro.ui.home.farmer.components

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.R
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.ui.SpeechRecognizerContract
import com.example.farmerpro.ui.home.farmer.farmViewModel
import com.example.farmerpro.ui.theme.Gray400
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun ItemRow(
    item: InventoryItem,
    viewModel: farmViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var voiceInput by remember { mutableStateOf("") }
    var selectedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    val permissionState = rememberPermissionState(
        permission = Manifest.permission.RECORD_AUDIO
    )
    SideEffect {
        permissionState.launchPermissionRequest()
    }

    val speechRecognizerLauncher = rememberLauncherForActivityResult(
        contract = SpeechRecognizerContract(),
        onResult = {
            if (!it.isNullOrEmpty() && it[0].toDoubleOrNull() != null) {
                    showDialog = true
                    voiceInput = it[0]
            }
        }
    )
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
                IconButton(onClick = {
                    selectedQuantity = if (selectedQuantity.toDouble().minus(1.0) >= 0.0) (selectedQuantity.toDouble().minus(1.0)).toString() else "0.0"
                    viewModel.updateInventoryItem(item.name, item.quantity - 1.0)
                }) {
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Subtract Item",
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
                                item.name,
                                newQuantity,
                                item.unit,
                                item.notes
                            )
                        }
                    )
                )
                IconButton(onClick = {
                    selectedQuantity = selectedQuantity.toDouble().plus(1.0).toString()
                    viewModel.updateInventoryItem(item.name, item.quantity + 1.0)
                }) {
                    Icon(
                        Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Add",
                        tint = LocalContentColor.current
                    )
                }
                IconButton(onClick = { if (permissionState.status.isGranted) {
                    speechRecognizerLauncher.launch(Unit)
                } else
                    permissionState.launchPermissionRequest()}) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_mic_24) ,
                        contentDescription = "Delete",
                        tint = LocalContentColor.current
                    )
                }

            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Are you sure you want to add $voiceInput ${item.name}?") },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        selectedQuantity = if (selectedQuantity.toDouble() + voiceInput.toDouble() >= 0) (selectedQuantity.toDouble() + voiceInput.toDouble()).toString() else selectedQuantity
                        viewModel.updateInventoryItem(item.name, item.quantity + voiceInput.toDouble(), item.unit, item.notes)
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}