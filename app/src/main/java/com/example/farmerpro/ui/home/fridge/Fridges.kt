package com.example.farmerpro.ui.home.fridge

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.domain.repository.FridgeRequests
import com.example.farmerpro.domain.repository.Fridges

@Composable
fun Fridges(
    viewModel: CommunityFridgeViewModel = hiltViewModel(), requestContent: @Composable (fridges: Fridges) -> Unit
) {
    when (val requestResponse = viewModel.fridgeResponse) {
        is Loading -> ProgressBar()
        is Success -> requestContent(requestResponse.data)
        is Failure -> print(requestResponse.e)
    }
}