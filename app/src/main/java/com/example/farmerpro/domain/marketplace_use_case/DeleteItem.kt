package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class DeleteItem(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(itemId: String) = repo.deleteItemFromFirestore(itemId)
}