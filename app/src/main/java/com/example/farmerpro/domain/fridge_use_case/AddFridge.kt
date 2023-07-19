package com.example.farmerpro.domain.fridge_use_case

import com.example.farmerpro.domain.repository.FridgeRepository

class AddFridge(
    private val repo: FridgeRepository
) {
    suspend operator fun invoke(
        fridge_name: String,
        uid: String,
        location: String,
        contact_number: String,
        imageUrl: String = "",
    ) = repo.addFridgeToFirestore(
        fridge_name, uid, location, contact_number, imageUrl
    )
}