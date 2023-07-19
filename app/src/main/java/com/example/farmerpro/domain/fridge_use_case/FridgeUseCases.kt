package com.example.farmerpro.domain.fridge_use_case



data class FridgeUseCases(
    val getItems: com.example.farmerpro.domain.fridge_use_case.GetFridges,
    val addItem: com.example.farmerpro.domain.fridge_use_case.AddFridge,
    val deleteItem: com.example.farmerpro.domain.fridge_use_case.DeleteFridge,
    val addImageToStorage: com.example.farmerpro.domain.fridge_use_case.AddImageToStorage
)