package com.example.farmerpro.domain.use_case

import com.example.farmerpro.domain.repository.BooksRepository

class AddBook(
    private val repo: BooksRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String
    ) = repo.addBookToFirestore(title, author)
}