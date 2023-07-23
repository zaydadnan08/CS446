package com.example.farmerpro.ui.home.fridge

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
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
import com.example.farmerpro.R
import com.example.farmerpro.Screens
import com.example.farmerpro.components.CircleButtonWithPlus
import com.example.farmerpro.domain.model.FridgeRequest
import com.example.farmerpro.ui.home.markets.components.ItemCard
import com.example.farmerpro.ui.home.markets.components.ItemDialog
import com.example.farmerpro.ui.home.markets.components.Items

@Composable
fun CommunityFridgeScreen(
    navController: NavController, viewModel: CommunityFridgeViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var selectedRequest by remember { mutableStateOf(FridgeRequest())}

    Column(
        verticalArrangement = Arrangement.Top, modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
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
                onClick = { openDialog = true },
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



        if (openDialog) {
            AddRequestDialog(
                closeDialog = {
                    openDialog = false
                },
                addRequest = { name, description, amount, location, fridgeName ->
                    viewModel.addRequest(name, description, amount, location, fridgeName)
                },
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
            CircleButtonWithPlus()
        }

        Spacer(modifier = Modifier.height(8.dp))
        fridge(
            name = "Community Fridge KW",
            address = "200 University Ave",
            distance = "0.1 km away",
            image = R.drawable.cf1
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
            name = "For-All Community",
            address = "75 King St S Unit 56",
            distance = "2.1 km away",
            image = R.drawable.cf2
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
            name = "Up Town Fridge",
            address = "200 King Street W",
            distance = "1.4 km away",
            image = R.drawable.cf3
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
            name = "Plant-based CF",
            address = "100 Water St N",
            distance = "8.7 km away",
            image = R.drawable.cf4
        )
        Spacer(modifier = Modifier.height(8.dp))
        fridge(
            name = "Guelph Fridge",
            address = "68 Wyndham St N",
            distance = "12.4 km away",
            image = R.drawable.cf5
        )
    }
}


