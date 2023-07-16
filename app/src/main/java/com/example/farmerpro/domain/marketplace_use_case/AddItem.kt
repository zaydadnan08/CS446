package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class AddItem(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(
        product_name: String,
        seller: String
    ) = repo.addItemToFirestore(product_name, seller)
}