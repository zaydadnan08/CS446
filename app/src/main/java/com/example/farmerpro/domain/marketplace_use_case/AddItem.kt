package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class AddItem(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(
        product_name: String,
        seller: String,
        price: String,
        location: String,
        contact_number: String
    ) = repo.addItemToFirestore(product_name, seller, price, location, contact_number)
}