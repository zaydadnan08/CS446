package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class AddItem(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(
        product_name: String,
        uid: String,
        seller: String,
        price: String,
        description: String,
        location: String,
        contact_number: String,
        imageUrl: String = "",
    ) = repo.addItemToFirestore(
        product_name, uid, seller, price, description, location, contact_number, imageUrl
    )
}