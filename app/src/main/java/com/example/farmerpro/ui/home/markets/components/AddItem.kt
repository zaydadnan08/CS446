package com.example.farmerpro.ui.home.markets.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.ui.home.markets.MarketViewModel

@Composable
fun AddItem(
    viewModel: MarketViewModel = hiltViewModel()
) {
    when(val addItemResponse = viewModel.addItemResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(addItemResponse.e)
    }
}