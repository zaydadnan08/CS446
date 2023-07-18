package com.example.farmerpro.ui.home.farmer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.domain.inventory_use_case.InventoryUseCases
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.repository.InventoryResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class farmViewModel @Inject constructor(
        private val useCases: InventoryUseCases,
        private val repository: AuthRepository
    ): ViewModel() {
    var addItemResponse by mutableStateOf<AddItemResponse>(Response.Success(false))
    var deleteItemResponse by mutableStateOf<DeleteItemResponse>(Response.Success(false))
    var inventoryResponse by mutableStateOf<InventoryResponse>(Response.Loading)
    var state by mutableStateOf(farmscreenstate())
        private set

    fun changeTextValue(text:String){
        viewModelScope.launch {
            state = state.copy(
                text = text
            )
        }
    }
    init {
        getFarmerInventory()
    }

    private fun getFarmerInventory() = viewModelScope.launch {
        var userId = repository.currentUser?.uid
        useCases.getItems(userId.toString()).collect { response ->
            inventoryResponse = response
        }
    }

    fun addItem(name: String, quantity: String) = viewModelScope.launch {
        var inventoryItem = InventoryItem(name, quantity.toDouble())
        var userId = repository.currentUser?.uid
        addItemResponse = Response.Loading
        if (userId != null) {
            addItemResponse = useCases.addItem(inventoryItem, userId)
            useCases.getItems(userId).collect { response ->
                inventoryResponse = response
            }
        }
    }

    fun deleteItem(name: String) = viewModelScope.launch {
        var items: InventoryItems = when(val itemsResponse = inventoryResponse) {
            is Response.Success -> itemsResponse.data
            else -> {
                InventoryItems(emptyList<InventoryItem>())
            }
        }
        var newItemList = items.inventory.filter {it.name != name}
        var newInventoryItems = InventoryItems(newItemList)
        var userId = repository.currentUser?.uid
        deleteItemResponse = Response.Loading
        if (userId != null) {
            deleteItemResponse = useCases.updateInventory(newInventoryItems, userId)
        }
    }

    fun incrementInventoryCount(name: String) = viewModelScope.launch {
        var items: InventoryItems = when(val itemsResponse = inventoryResponse) {
            is Response.Success -> itemsResponse.data
            else -> {
                InventoryItems(emptyList<InventoryItem>())
            }
        }
        var itemList = items.inventory.map {
            if (it.name === name) {
                InventoryItem(it.name, it.quantity + 1.0)
            } else {
                it
            }
        }
        var newInventoryItems = InventoryItems(itemList)
        var userId = repository.currentUser?.uid
        if (userId != null) {
            useCases.updateInventory(newInventoryItems, userId)
        }
    }

    fun decrementInventoryCount(name: String) = viewModelScope.launch {
        var items: InventoryItems = when(val itemsResponse = inventoryResponse) {
            is Response.Success -> itemsResponse.data
            else -> {
                InventoryItems(emptyList<InventoryItem>())
            }
        }
        var itemList = items.inventory.map {
            if (it.name === name) {
                InventoryItem(it.name, it.quantity - 1.0)
            } else {
                it
            }
        }
        var newInventoryItems = InventoryItems(itemList)
        var userId = repository.currentUser?.uid
        if (userId != null) {
            useCases.updateInventory(newInventoryItems, userId)
        }
    }
}