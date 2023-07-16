package com.example.farmerpro.domain.marketplace_use_case

import com.example.farmerpro.domain.repository.MarketRepository

class AddBook(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String
    ) = repo.addBookToFirestore(title, author)
}