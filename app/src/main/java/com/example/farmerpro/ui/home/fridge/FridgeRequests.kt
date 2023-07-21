package com.example.farmerpro.ui.home.fridge

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.domain.repository.FridgeRequests

@Composable
fun FridgeRequests(
    viewModel: CommunityFridgeViewModel = hiltViewModel(), requestContent: @Composable (requests: FridgeRequests) -> Unit
) {
    when (val requestResponse = viewModel.requestResponse) {
        is Loading -> ProgressBar()
        is Success -> requestContent(requestResponse.data)
        is Failure -> print(requestResponse.e)
    }
}