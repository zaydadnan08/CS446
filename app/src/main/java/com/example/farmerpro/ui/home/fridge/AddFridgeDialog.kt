package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmerpro.components.GreyTextInput
import com.example.farmerpro.domain.model.CameraResponse

import com.example.farmerpro.ui.home.markets.MarketViewModel
import kotlinx.coroutines.job

@Composable
fun AddFridgeDialog(
    closeDialog: () -> Unit,
    addItem: (
        name: String,
        location: String,
        fridgeinventory: String
    ) -> Unit,
    openGallery: () -> Unit,
    viewModel: CommunityFridgeViewModel
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var fridgeinventory by remember { mutableStateOf("") }

    val focusRequester = FocusRequester()

    AlertDialog(onDismissRequest = closeDialog, text = {
        LazyColumn {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Add New Fridge", modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                )
                IconButton(
                    onClick = {
                        openGallery()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFFE5E5E5), RoundedCornerShape(8.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.DarkGray,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Box(modifier = Modifier.fillMaxSize()) {
                    when (val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
                        is CameraResponse.Loading -> {
                            Text(
                                text = "Adding image ...",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        is CameraResponse.Success -> addImageToStorageResponse.data?.let { downloadUrl ->
                            viewModel.setDownloadUrl(downloadUrl.toString())
                            Text(
                                text = "Image added successfully",
                                color = Color(0xFF067f00),
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        is CameraResponse.Failure -> {
                            Text(
                                text = "Error adding image",
                                color = Color(0xFF067f00),
                                modifier = Modifier.align(Alignment.Center)
                            )
                            print(addImageToStorageResponse.e)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = "Fridge Name",
                    Modifier.focusRequester(focusRequester)
                )
                LaunchedEffect(Unit) {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                GreyTextInput(
                    value = location, onValueChange = { location = it }, placeholder = "Location"
                )
                Spacer(modifier = Modifier.height(8.dp))

                GreyTextInput(
                    value = fridgeinventory,
                    onValueChange = { fridgeinventory = it },
                    placeholder = "Fridge Inventory",
                    maxLines = 8,
                    //imeOptions = ImeAction.Next,
                )

            }
        }
    }, confirmButton = {
        val addImageToStorageResponse = viewModel.addImageToStorageResponse
        val isEnabled = !(addImageToStorageResponse is CameraResponse.Loading) && name.isNotEmpty() && location.isNotEmpty() && fridgeinventory.isNotEmpty()
        TextButton(
            onClick = {
                if (isEnabled) {
                    closeDialog()
                    addItem(name, location, fridgeinventory)
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