package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.repository.FarmerRepository

class DeleteFarmer(
    private val repo: FarmerRepository
) {
    suspend operator fun invoke(farmerID: String) = repo.deleteItemFromFirestore(farmerID)
}