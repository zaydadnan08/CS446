package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class GetItems (
    private val repo: MarketRepository
) {
    operator fun invoke() = repo.getItemsFromFirestore()
}