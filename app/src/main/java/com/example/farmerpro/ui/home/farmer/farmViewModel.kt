package com.example.farmerpro.ui.home.farmer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.farmerpro.Screens
import com.example.farmerpro.domain.inventory_use_case.InventoryUseCases
import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.repository.InventoryResponse
import com.example.farmerpro.domain.repository.SalesResponse
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
    var salesResponse by mutableStateOf<SalesResponse>(Response.Loading)


    init {
        getFarmerInventory()
        getSaleRecords()
    }

    private fun getFarmerInventory() = viewModelScope.launch {
        var userId = repository.currentUser?.uid
        useCases.getItems(userId.toString()).collect { response ->
            inventoryResponse = response
        }
    }

    fun addItem(name: String, quantity: String, unit: String, notes: String) = viewModelScope.launch {
        var inventoryItem = InventoryItem(name, quantity.toDouble(), unit, notes)
        var userId = repository.currentUser?.uid
        addItemResponse = Response.Loading
        if (userId != null && quantity.toDoubleOrNull() != null && quantity.toDouble() >= 0.0) {
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

    fun updateSalesRecord(name: String, price: Double, quantity: Double) = viewModelScope.launch {
        var sales: SaleRecords = when(val salesResponse = salesResponse) {
            is Response.Success -> salesResponse.data
            else -> {
                SaleRecords(emptyList<SaleRecord>())
            }
        }
        var newItemList = sales.sales.filter {it.name != name || it.price != price || it.quantity != quantity}
        var newSaleRecords = SaleRecords(newItemList)
        var userId = repository.currentUser?.uid
        deleteItemResponse = Response.Loading
        if (userId != null) {
            deleteItemResponse = useCases.updateSales(newSaleRecords, userId)
        }
    }

    fun updateInventoryItem(name: String, quantity: Double? = null, unit: String? = null, notes: String? = null) = viewModelScope.launch {
        var items: InventoryItems = when(val itemsResponse = inventoryResponse) {
            is Response.Success -> itemsResponse.data
            else -> {
                InventoryItems(emptyList<InventoryItem>())
            }
        }
        if (quantity != null && quantity >= 0.0) {
            var itemList = items.inventory.map {
                if (it.name.equals(name)) {
                    InventoryItem(
                        it.name,
                        quantity ?: it.quantity,
                        unit ?: it.unit,
                        notes ?: it.notes
                    )
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

    fun trackSaleRecord(name: String, quantity: Double, unit: String, price: Double) {
        var saleRecord = SaleRecord(name, quantity, unit, price)
        var userId = repository.currentUser?.uid
        if (userId != null) {
            useCases.trackSale(saleRecord, userId)
        }
    }

    private fun getSaleRecords() = viewModelScope.launch {
        var userId = repository.currentUser?.uid
        useCases.getSales(userId.toString()).collect { response ->
            salesResponse = response
        }
    }

    fun signOut() {
        repository.signOut()
    }
}