package com.example.farmerpro.domain.repository

import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias InventoryResponse = Response<InventoryItems>

interface FarmerRepository {
    fun getItemsFromFirestore(farmerID: String): Flow<InventoryResponse>

    suspend fun addItemToFirestore(inventory: InventoryItems, farmerID: String): Response<Boolean>

    suspend fun deleteItemFromFirestore(farmerID: String): Response<Boolean>
}