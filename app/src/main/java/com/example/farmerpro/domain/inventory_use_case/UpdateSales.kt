package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.model.SaleRecords
import com.example.farmerpro.domain.repository.FarmerRepository

class UpdateSales(
    private val repo: FarmerRepository
) {
    operator fun invoke(
        saleRecords: SaleRecords,
        farmerID: String
    ) = repo.updateSalesItems(saleRecords, farmerID)
}