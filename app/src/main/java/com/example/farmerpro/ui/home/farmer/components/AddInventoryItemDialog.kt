package com.example.farmerpro.ui.home.farmer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.components.Dropdown
import com.example.farmerpro.components.GreyTextInput
import kotlinx.coroutines.job

@Composable
fun AddInventoryAlertDialog(
    closeDialog: () -> Unit,
    addItem: (name: String, quantity: String, unit: String, notes: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var unit by remember { mutableStateOf("lbs") }

    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        text = {
            Column {
                Text(
                    text = "Add inventory",
                    modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )

                GreyTextInput(value = name, onValueChange = { name = it } , placeholder = "Item Name", Modifier.focusRequester(focusRequester))
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                GreyTextInput(value = quantity, onValueChange =  { quantity = it } , placeholder = "Quantity")

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp, vertical = 0.dp)
                ) {
                    Text(
                        text = "Unit",
                        style = TextStyle(
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    )

                    Dropdown(
                        options = listOf("lbs", "kg", "cnt"),
                        onOptionSelected = { unit = it },
                        selectedOption = unit
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(
                    value = notes,
                    onValueChange = { notes = it },
                    placeholder = "Notes (Optional)"
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (quantity.toDoubleOrNull() != null && name != "") {
                        closeDialog()
                        addItem(name, quantity, unit, notes)
                    }

                }
            ) {
                Text(
                    text = "Add"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = closeDialog
            ) {
                Text(
                    text = "Dismiss"
                )
            }
        }
    )
}