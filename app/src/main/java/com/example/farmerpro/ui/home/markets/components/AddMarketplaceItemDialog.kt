package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.job

@Composable
fun AddItemAlertDialog(
    closeDialog: () -> Unit,
    addItem: (product_name: String, seller: String) -> Unit
) {
    var product_name by remember { mutableStateOf("") }
    var seller by remember { mutableStateOf("") }
    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        title = {
            Text(
                text = "Add product listing"
            )
        },
        text = {
            Column {
                TextField(
                    value = product_name,
                    onValueChange = { product_name = it },
                    placeholder = {
                        Text(
                            text = "PRODUCT_NAME"
                        )
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                Spacer(
                    modifier = Modifier.height(16.dp)
                )
                TextField(
                    value = seller,
                    onValueChange = { seller = it },
                    placeholder = {
                        Text(
                            text = "SELLER"
                        )
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    closeDialog()
                    addItem(product_name, seller)
                }
            ) {
                Text(
                    text = "ADD"
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = closeDialog
            ) {
                Text(
                    text = "DISMISS"
                )
            }
        }
    )
}