package com.example.farmerpro.domain.fridge_use_case

import com.example.farmerpro.domain.repository.FridgeRepository

class UpdateFridge(
    private val repo: FridgeRepository
) {
    suspend operator fun invoke(
        itemId: String,
        fridgeInventory: String = "",
    ) = repo.updateFridgeFromFirestore(itemId = itemId, fridgeInventory = fridgeInventory)
}