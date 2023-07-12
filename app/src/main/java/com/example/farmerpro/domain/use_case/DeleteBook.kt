package com.example.farmerpro.domain.use_case

import com.example.farmerpro.domain.repository.BooksRepository

class DeleteBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(bookId: String) = repo.deleteBookFromFirestore(bookId)
}