package com.example.farmerpro.data

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.Book
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddBookResponse
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.repository.DeleteBookResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarketRepositoryImpl @Inject constructor(
    private val booksRef: CollectionReference
): MarketRepository {
    override fun getBooksFromFirestore() = callbackFlow {
        val snapshotListener = booksRef.orderBy("title").addSnapshotListener { snapshot, e ->
            val booksResponse = if (snapshot != null) {
                val books = snapshot.toObjects(Book::class.java)
                Success(books)
            } else {
                Failure(e)
            }
            trySend(booksResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addBookToFirestore(title: String, author: String): AddBookResponse = try {
        val id = booksRef.document().id
        val book = Book(
            id = id,
            title = title,
            author = author
        )
        booksRef.document(id).set(book).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteBookFromFirestore(bookId: String): DeleteBookResponse = try {
        booksRef.document(bookId).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}