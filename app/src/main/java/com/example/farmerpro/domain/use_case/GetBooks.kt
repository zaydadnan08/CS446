package com.example.farmerpro.domain.use_case

import com.example.farmerpro.domain.repository.MarketRepository

class GetBooks (
    private val repo: MarketRepository
) {
    operator fun invoke() = repo.getBooksFromFirestore()
}