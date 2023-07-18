package com.example.farmerpro.domain.model

data class InventoryItem(
    var name: String = "",
    var quantity: Double = 0.0,
    var unit: String = "lbs",
    var notes: String? = null,
)

data class InventoryItems(
    var inventory: List<InventoryItem> = emptyList<InventoryItem>()
)