package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.repository.FarmerRepository

class AddOrUpdateInventory(
    private val repo: FarmerRepository
) {
    suspend operator fun invoke(
        inventoryItem: InventoryItem,
        farmerID: String
    ) = repo.addItemToFirestore(inventoryItem, farmerID)
}