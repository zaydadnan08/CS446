package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.repository.MarketRepository

class UpdateItem(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(itemId: String, rating: Double, item: MarketplaceItem) = repo.updateRating(itemId, item, rating)
}