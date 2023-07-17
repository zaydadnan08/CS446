package com.example.farmerpro.domain.model

data class InventoryItem(
    var imageUrl: String? = null,
    var name: String? = null,
    var notes: String? = null,
    var quantity: Int = 0,
    var unit: String? = null,
)

data class InventoryItems(
    var inventoryItems: Array<InventoryItem>
)