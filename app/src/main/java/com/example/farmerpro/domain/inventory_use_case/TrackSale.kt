package com.example.farmerpro.domain.inventory_use_case

import com.example.farmerpro.domain.model.SaleRecord
import com.example.farmerpro.domain.repository.FarmerRepository

class TrackSale(
    private val repo: FarmerRepository
) {
    operator fun invoke(
        saleRecord: SaleRecord,
        farmerID: String
    ) = repo.addSaleRecord(saleRecord, farmerID)
}