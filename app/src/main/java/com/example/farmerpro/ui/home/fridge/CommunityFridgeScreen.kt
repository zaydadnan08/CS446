package com.example.farmerpro.ui.home.fridge

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.farmerpro.Screens
import com.example.farmerpro.components.CircleButtonWithPlus
import com.example.farmerpro.core.Constants
import com.example.farmerpro.domain.model.FridgeRequest
import com.example.farmerpro.ui.home.markets.components.ItemCard
import com.example.farmerpro.ui.home.markets.components.ItemDialog
import com.example.farmerpro.ui.home.markets.components.Items

@Composable
fun CommunityFridgeScreen(
    navController: NavController, viewModel: CommunityFridgeViewModel = hiltViewModel()
) {
    var openRequestDialog by remember { mutableStateOf(false) }
    var openFridgeDialog by remember { mutableStateOf(false) }

    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
            imageUri?.let {
                viewModel.addImageToStorage(imageUri)
            }
        }
    var openDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedRequest by remember { mutableStateOf(FridgeRequest())}

    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Community Fridge", modifier = Modifier.padding(
                bottom = 8.dp, top = 12.dp, start = 8.dp, end = 4.dp
            ), style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 28.sp, textAlign = TextAlign.Start
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            onClick = {
                viewModel.signOut()
                navController.navigate(Screens.Start.name)
            },
            shape = CircleShape,
            border = BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Blue)
        ) {
            Text(
                text = "Sort by: distance", modifier = Modifier.padding(), style = TextStyle(
                    fontWeight = FontWeight.Medium, fontSize = 14.sp, textAlign = TextAlign.Start
                )
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Requests", modifier = Modifier.padding(4.dp), style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Start
                )
            )
            CircleButtonWithPlus(
                onClick = { openRequestDialog = true },
            )
        }

        FridgeRequests(requestContent = { requests ->
            val filteredRequests = if (true) {
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
                requests.forEach { request ->
                    item {
                        RequestCard(request = request, onCardClick = {
                            selectedRequest = it
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
                addItem = { name, location ->
                    viewModel.addFridge(name, location)
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Fridges", modifier = Modifier.padding(4.dp), style = TextStyle(
                    fontWeight = FontWeight.Bold, fontSize = 24.sp, textAlign = TextAlign.Start
                )
            )
            CircleButtonWithPlus(
                onClick = { openFridgeDialog = true },
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Fridges(requestContent = { requests ->
            val filteredRequests = if (true) {
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
                        FridgeCard(fridgeItem = request, onCardClick = {
                            // selectedItem = it
                            //  showDialog = true
                        })
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        })
    }
}


