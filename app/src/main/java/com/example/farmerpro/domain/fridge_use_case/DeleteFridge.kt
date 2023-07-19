package com.example.farmerpro.domain.fridge_use_case

import com.example.farmerpro.domain.repository.FridgeRepository

class DeleteFridge(
    private val repo: FridgeRepository
) {
    suspend operator fun invoke(itemId: String) = repo.deleteFridgeFromFirestore(itemId)
}