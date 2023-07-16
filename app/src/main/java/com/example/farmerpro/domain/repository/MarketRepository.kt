package com.example.farmerpro.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response

typealias Books = List<MarketplaceItem>
typealias BooksResponse = Response<Books>
typealias AddBookResponse = Response<Boolean>
typealias DeleteBookResponse = Response<Boolean>

interface MarketRepository {
    fun getBooksFromFirestore(): Flow<BooksResponse>

    suspend fun addBookToFirestore(title: String, author: String): AddBookResponse

    suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse
}