package com.example.farmerpro.domain.use_case

import com.example.farmerpro.domain.repository.BooksRepository

class GetBooks (
    private val repo: BooksRepository
) {
    operator fun invoke() = repo.getBooksFromFirestore()
}