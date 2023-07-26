package com.example.farmerpro.domain.inventory_use_case

data class InventoryUseCases (
    val getItems: GetInventoryByFarmer,
    val addItem: AddOrUpdateInventory,
    val updateInventory: UpdateInventory,
    val getSales: GetSalesByFarmer,
    val trackSale: TrackSale,
    val updateSales: UpdateSales
)