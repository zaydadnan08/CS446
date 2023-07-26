package com.example.farmerpro.domain.fridge_use_case

import com.example.farmerpro.domain.model.FridgeItem


data class FridgeUseCases(
    val getFridges: GetFridges,
    val deleteFridge: DeleteFridge,
    val addFridge: AddFridge,
    val updateFridge: UpdateFridge,
    val addImageToStorage: AddImageToStorage,
)