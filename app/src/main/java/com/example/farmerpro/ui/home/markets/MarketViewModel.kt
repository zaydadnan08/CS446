package com.example.farmerpro.ui.home.markets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.Response.Loading
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.ItemResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.marketplace_use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var itemsResponse by mutableStateOf<ItemResponse>(Loading)
        private set
    var addItemResponse by mutableStateOf<AddItemResponse>(Success(false))
        private set
    var deleteItemResponse by mutableStateOf<DeleteItemResponse>(Success(false))
        private set

    init {
        getItems()
    }

    private fun getItems() = viewModelScope.launch {
        useCases.getItems().collect { response ->
            itemsResponse = response
        }
    }

    fun addItem(product_name: String, seller: String, price: String, location: String, contact_number: String) = viewModelScope.launch {
        addItemResponse = Loading
        addItemResponse = useCases.addItem(product_name, seller, price, location, contact_number)
    }

    fun deleteItem(ItemId: String) = viewModelScope.launch {
        deleteItemResponse = Loading
        deleteItemResponse = useCases.deleteItem(ItemId)
    }
}