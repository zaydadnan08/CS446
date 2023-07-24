package com.example.farmerpro.ui.home.farmer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.components.GreyTextInput
import com.example.farmerpro.ui.home.farmer.farmViewModel
import kotlinx.coroutines.job

@Composable
fun TrackSaleDialog(
    navController: NavController,
    closeDialog: () -> Unit,
    name: String,
    origQuantity: Double,
    unit: String,
    viewModel: farmViewModel = hiltViewModel()
) {
    var quantity by remember { mutableStateOf("") }

    var price by remember { mutableStateOf("") }

    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        text = {
            Column {
                Text(
                    text = "Track Sale",
                    modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )

                GreyTextInput(
                    value = quantity,
                    onValueChange = { quantity = it },
                    placeholder = "Quantity Sold ($unit)",
                    Modifier.focusRequester(focusRequester))
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = price, onValueChange =  { price = it } , placeholder = "Total Price")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.updateInventoryItem(name, origQuantity.minus(quantity.toDouble()))
                    viewModel.trackSaleRecord(name, quantity.toDouble(), price.toDouble())
                    navController.popBackStack()
                    closeDialog()
                }
            ) {
                Text(
                    text = "Save"
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