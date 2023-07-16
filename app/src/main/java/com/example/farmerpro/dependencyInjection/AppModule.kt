package com.example.farmerpro.dependencyInjection

import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.data.AuthRepositoryImpl
import com.example.farmerpro.data.MarketRepositoryImpl
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.marketplace_use_case.AddBook
import com.example.farmerpro.domain.marketplace_use_case.DeleteBook
import com.example.farmerpro.domain.marketplace_use_case.GetBooks
import com.example.farmerpro.domain.marketplace_use_case.UseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideBooksRef() = Firebase.firestore.collection("books")

    @Provides
    fun provideBooksRepository(
        booksRef: CollectionReference
    ): MarketRepository = MarketRepositoryImpl(booksRef)

    @Provides
    fun provideUseCases(
        repo: MarketRepository
    ) = UseCases(
        getBooks = GetBooks(repo),
        addBook = AddBook(repo),
        deleteBook = DeleteBook(repo)
    )
    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

}