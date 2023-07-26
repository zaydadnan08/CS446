package com.example.farmerpro.domain.model

data class SaleRecord(
    var name: String = "",
    var quantity: Double = 0.0,
    var unit: String = "lbs",
    var price: Double = 0.0,
)

data class SaleRecords(
    var sales: List<SaleRecord> = emptyList<SaleRecord>()
)

data class InventoryItem(
    var name: String = "",
    var quantity: Double = 0.0,
    var unit: String = "lbs",
    var notes: String = "",
)

data class InventoryItems(
    var inventory: List<InventoryItem> = emptyList<InventoryItem>()
)