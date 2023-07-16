package com.example.farmerpro.dependencyInjection

import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.data.AuthRepositoryImpl
import com.example.farmerpro.data.MarketRepositoryImpl
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.marketplace_use_case.AddItem
import com.example.farmerpro.domain.marketplace_use_case.DeleteItem
import com.example.farmerpro.domain.marketplace_use_case.GetItems
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
    fun provideItemsRef() = Firebase.firestore.collection("items")

    @Provides
    fun provideItemsRepository(
        itemsRef: CollectionReference
    ): MarketRepository = MarketRepositoryImpl(itemsRef)

    @Provides
    fun provideUseCases(
        repo: MarketRepository
    ) = UseCases(
        getItems = GetItems(repo),
        addItem = AddItem(repo),
        deleteItem = DeleteItem(repo)
    )
    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

}