package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.repository.FarmerRepository

class GetInventoryByFarmer (
    private val repo: FarmerRepository
) {
    operator fun invoke(farmerID: String) = repo.getItemsFromFirestore(farmerID)
}