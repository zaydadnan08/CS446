package com.example.farmerpro.domain.repository

import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias InventoryResponse = Response<InventoryItems>

interface FarmerRepository {
    fun getItemsFromFirestore(farmerID: String): Flow<InventoryResponse>

    suspend fun addItemToFirestore(inventoryItem: InventoryItem, farmerID: String): Response<Boolean>

    suspend fun updateInventoryItems(newInventoryItems: InventoryItems, farmerID: String): Response<Boolean>
}