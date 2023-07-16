package com.example.farmerpro.ui.home.markets.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.domain.repository.Items
import com.example.farmerpro.ui.home.markets.MarketViewModel

@Composable
fun Items(
    viewModel: MarketViewModel = hiltViewModel(),
    itemsContent: @Composable (items: Items) -> Unit
) {
    when(val itemsResponse = viewModel.itemsResponse) {
        is Loading -> ProgressBar()
        is Success -> itemsContent(itemsResponse.data)
        is Failure -> print(itemsResponse.e)
    }
}