package com.example.farmerpro.ui.home.markets

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.Response.Loading
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.ItemResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.marketplace_use_case.MarketUseCases
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AuthRepository
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val marketUseCases: MarketUseCases,
    repository: AuthRepository
) : BaseViewModel(repository) {
    var itemsResponse by mutableStateOf<ItemResponse>(Loading)
    var addImageToStorageResponse by mutableStateOf<CameraResponse<Uri>>(CameraResponse.Success(null))
        private set
    var addItemResponse by mutableStateOf<AddItemResponse>(Success(false))
        private set
    var deleteItemResponse by mutableStateOf<DeleteItemResponse>(Success(false))
        private set

    var updateItemResponse by mutableStateOf<Response<Boolean>>(Success(false))
        private set

    init {
        getItems()
    }

    private fun getItems() = viewModelScope.launch {
        marketUseCases.getItems().collect { response ->
            itemsResponse = response
        }
    }

    fun addItem(product_name: String, price: String, description: String, location: String) =
        viewModelScope.launch {
            addItemResponse = Loading
            addItemResponse = marketUseCases.addItem(
                product_name,
                userId.value ?: "",
                currentUser.value?.name ?: "",
                price,
                description,
                location,
                currentUser.value?.contactNumber ?: "",
                downloadUrl.value
            )
            marketUseCases.getItems().collect { response ->
                itemsResponse = response
            }
        }

    override fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = CameraResponse.Loading
        addImageToStorageResponse =
            marketUseCases.addImageToStorage(imageUri, (0..100).random().toString())
    }


    fun deleteItem(ItemId: String) = viewModelScope.launch {
        deleteItemResponse = Loading
        deleteItemResponse = marketUseCases.deleteItem(ItemId)
    }

    fun updateItem(ItemId: String, item: MarketplaceItem, rating: Double) = viewModelScope.launch {
        updateItemResponse = Loading
        updateItemResponse = marketUseCases.updateItem(ItemId, rating, item)
    }
}