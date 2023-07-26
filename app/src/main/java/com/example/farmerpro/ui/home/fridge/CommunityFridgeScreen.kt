package com.example.farmerpro.ui.home.fridge

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.Screens
import com.example.farmerpro.components.CircleButtonWithPlus
import com.example.farmerpro.components.Title
import com.example.farmerpro.core.Constants
import com.example.farmerpro.domain.model.FridgeItem
import com.example.farmerpro.domain.model.FridgeRequest
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults

@Composable
fun CommunityFridgeScreen(
    navController: NavController, viewModel: CommunityFridgeViewModel = hiltViewModel(), userType: String
) {
    var openRequestDialog by remember { mutableStateOf(false) }
    var openFridgeDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                viewModel.addImageToStorage(imageUri)
            }
        }
    var openDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var showDialog2 by remember { mutableStateOf(false) }
    var selectedRequest by remember { mutableStateOf(FridgeRequest()) }
    var selectedFridge by remember { mutableStateOf(FridgeItem()) }

    var isCheckedRequests by remember { mutableStateOf(false) }
    var isCheckedFridges by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Title(text = "Community Fridge", onClick = {
            viewModel.signOut()
            navController.navigate(Screens.Start.name)
        })
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp)
            ) {
                Text(
                    text = "Requests", modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Start
                    )
                )
                if(userType == "Admin") {
                    CircleButtonWithPlus(
                        onClick = { openRequestDialog = true },
                    )
                }
            }
            Switch(
                checked = isCheckedRequests,
                onCheckedChange = { isCheckedRequests = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Black,
                    uncheckedThumbColor = Color.Black,
                    uncheckedTrackColor = Color.White
                )
            )
        }

        FridgeRequests(requestContent = { requests ->
            val filteredRequests = if (isCheckedRequests) {
                requests.filter { request ->
                    request.uid == viewModel.userId.value
                }
            } else {
                requests
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(1), modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                filteredRequests.forEach { request ->
                    item {
                        RequestCard(request = request, onCardClick = {
                            selectedRequest = request
                            showDialog = true
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

        })
        if (openRequestDialog) {
            AddRequestDialog(
                closeDialog = {
                    openRequestDialog = false
                },
                addRequest = { name, description, amount, location, fridgeName ->
                    viewModel.addRequest(name, description, amount, location, fridgeName)
                },
            )
        }
        if (openFridgeDialog) {
            AddFridgeDialog(
                closeDialog = {
                    openFridgeDialog = false
                },
                addItem = { name, location, fridgeinventory, ->
                    viewModel.addFridge(name, location, fridgeinventory)
                },
                openGallery = {
                    galleryLauncher.launch(Constants.ALL_IMAGES)
                },
                viewModel = viewModel
            )
        }
        if (showDialog) {
            RequestDialog(
                closeDialog = { showDialog = false },
                request = selectedRequest,
                owner = selectedRequest.uid == viewModel.userId.value,
                deleteRequest = {
                    selectedRequest.id?.let { itemId ->
                        viewModel.deleteRequest(itemId)
                    }
                },
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp)
            ) {
                Text(
                    text = "Fridges", modifier = Modifier.padding(4.dp), style = TextStyle(
                        fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Start
                    )
                )
                if(userType == "Admin") {
                    CircleButtonWithPlus(
                        onClick = { openFridgeDialog = true },
                    )
                }
            }
                Switch(
                    checked = isCheckedFridges,
                    onCheckedChange = { isCheckedFridges = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = Color.Black,
                        uncheckedThumbColor = Color.Black,
                        uncheckedTrackColor = Color.White
                    )
                )
        }

        Spacer(modifier = Modifier.height(8.dp))
        Fridges(requestContent = { fridges ->
            val filteredFridges = if (isCheckedFridges) {
                fridges.filter { fridge ->
                    fridge.uid == viewModel.userId.value
                }
            } else {
                fridges
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(1), modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                    .height(340.dp)
            ) {
                filteredFridges.forEach { fridge ->
                    item {
                        FridgeCard(fridgeItem = fridge, onCardClick = {
                            selectedFridge = fridge
                            showDialog2 = true
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        })
        if (showDialog2) {
            FridgeDialog(
                closeDialog = { showDialog2 = false },
                fridge = selectedFridge,
                owner = selectedFridge.uid == viewModel.userId.value,
                deleteFridge = {
                    selectedFridge.id?.let { itemId ->
                        viewModel.deleteFridge(itemId)
                    }
                },
            )
        }
    }
}



