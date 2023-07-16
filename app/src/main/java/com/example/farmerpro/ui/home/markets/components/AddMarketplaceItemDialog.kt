package com.example.farmerpro.ui.home.markets.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.components.GreyTextInput
import kotlinx.coroutines.job

@Composable
fun AddItemAlertDialog(
    closeDialog: () -> Unit,
    addItem: (product_name: String, seller: String, price: String, location: String, contact_number: String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var seller by remember { mutableStateOf("") }
    var price_per_lb by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    val focusRequester = FocusRequester()

    AlertDialog(
        onDismissRequest = closeDialog,
        text = {
            Column {
                Text(
                    text = "Add product",
                    modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )

                IconButton(
                    onClick = {
                              //add image here
                    } ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFFE5E5E5), RoundedCornerShape(8.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(CenterHorizontally)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.DarkGray,
                            modifier = Modifier
                                    .align(Alignment.Center)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = name, onValueChange = { name = it } , placeholder = "Product Name", Modifier.focusRequester(focusRequester))
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = seller, onValueChange =  { seller = it } , placeholder = "seller")

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = price_per_lb, onValueChange =  { price_per_lb = it } , placeholder = "Price per lb")

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = location, onValueChange =  { location = it } , placeholder = "Location")

                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(value = number, onValueChange =  { number = it } , placeholder = "Contact Number")

            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    closeDialog()
                    addItem(name, seller, price_per_lb, location, number)
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