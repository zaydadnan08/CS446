package com.example.farmerpro.domain.marketplace_use_case

data class MarketUseCases(
    val getItems: GetItems,
    val addItem: AddItem,
    val deleteItem: DeleteItem,
    val addImageToStorage: AddImageToStorage,
    val updateItem: UpdateItem
)