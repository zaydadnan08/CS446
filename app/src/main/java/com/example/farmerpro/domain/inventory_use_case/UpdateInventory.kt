package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.repository.FarmerRepository

class UpdateInventory(
    private val repo: FarmerRepository
) {
    suspend operator fun invoke(newInventoryItems: InventoryItems, farmerId: String) = repo.updateInventoryItems(newInventoryItems, farmerId)
}