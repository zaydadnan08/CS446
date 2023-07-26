package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.components.GreyTextInput
import kotlinx.coroutines.job

@Composable
fun AddRequestDialog(
    closeDialog: () -> Unit,
    addRequest: (
        product_name: String,
        description: String,
        amount: String,
        location: String,
        fridge_name: String,
    ) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var fridgeName by remember { mutableStateOf("") }

    val focusRequester = FocusRequester()

    AlertDialog(onDismissRequest = closeDialog, text = {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Add Request", modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Product Name",
                    Modifier.focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
            Spacer(modifier = Modifier.height(8.dp))
            GreyTextInput(
                value = amount,
                onValueChange = { amount = it },
                placeholder = "Amount Requesting (lb)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.height(8.dp))
            GreyTextInput(
                value = description,
                onValueChange = { description = it },
                placeholder = "Product Description (optional)"
            )
            Spacer(modifier = Modifier.height(8.dp))
            GreyTextInput(
                value = fridgeName,
                onValueChange = { fridgeName = it },
                placeholder = "Fridge Name"
            )
            Spacer(modifier = Modifier.height(8.dp))
            GreyTextInput(
                value = location, onValueChange = { location = it }, placeholder = "Location"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
    }, confirmButton = {
        val isEnabled = name.isNotEmpty() && amount.isNotEmpty() && location.isNotEmpty() && fridgeName.isNotEmpty()
        TextButton(

            onClick = {
                if (isEnabled) {
                    closeDialog()
                    addRequest(name, description, amount, location, fridgeName)
                }
            }, enabled = isEnabled
        ) {
            Text(
                text = "Add"
            )
        }
    }, dismissButton = {
        TextButton(
            onClick = closeDialog
        ) {
            Text(
                text = "Dismiss"
            )
        }
    })
}
