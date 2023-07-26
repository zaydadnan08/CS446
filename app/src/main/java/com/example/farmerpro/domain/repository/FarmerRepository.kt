package com.example.farmerpro.domain.repository

import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import kotlinx.coroutines.flow.Flow

typealias InventoryResponse = Response<InventoryItems>
typealias SalesResponse = Response<SaleRecords>

interface FarmerRepository {
    fun getItemsFromFirestore(farmerID: String): Flow<InventoryResponse>
    fun getSalesFromFirestore(farmerID: String): Flow<SalesResponse>

    suspend fun addItemToFirestore(
        inventoryItem: InventoryItem, farmerID: String
    ): Response<Boolean>

    fun addSaleRecord(
        saleRecord: SaleRecord, farmerID: String
    ): Response<Boolean>

    suspend fun updateInventoryItems(
        newInventoryItems: InventoryItems, farmerID: String
    ): Response<Boolean>
}