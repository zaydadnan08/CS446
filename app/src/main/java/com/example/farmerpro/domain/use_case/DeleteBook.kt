package com.example.farmerpro.domain.use_case

import com.example.farmerpro.domain.repository.MarketRepository

class DeleteBook(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteBookFromFirestore(bookId)
}