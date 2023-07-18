package com.example.farmerpro.ui.home.markets.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.ui.home.markets.MarketViewModel

@Composable
fun DeleteItem(
    viewModel: MarketViewModel = hiltViewModel()
) {
    when (val deleteItemResponse = viewModel.deleteItemResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(deleteItemResponse.e)
    }
}