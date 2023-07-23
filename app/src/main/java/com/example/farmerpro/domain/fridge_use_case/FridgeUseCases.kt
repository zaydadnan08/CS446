package com.example.farmerpro.domain.fridge_use_case



data class FridgeUseCases(
    val getFridges: GetFridges,
    val deleteFridge: DeleteFridge,
    val addFridge: AddFridge,
    val addImageToStorage: AddImageToStorage,
)