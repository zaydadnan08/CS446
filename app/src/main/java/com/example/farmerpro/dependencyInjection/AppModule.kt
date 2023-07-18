package com.example.farmerpro.dependencyInjection

import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.data.AuthRepositoryImpl
import com.example.farmerpro.data.FarmerRepositoryImpl
import com.example.farmerpro.data.MarketRepositoryImpl
import com.example.farmerpro.domain.inventory_use_case.AddOrUpdateInventory
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.marketplace_use_case.AddItem
import com.example.farmerpro.domain.marketplace_use_case.DeleteItem
import com.example.farmerpro.domain.marketplace_use_case.GetItems
import com.example.farmerpro.domain.marketplace_use_case.UseCases
import com.example.farmerpro.domain.inventory_use_case.GetInventoryByFarmer
import com.example.farmerpro.domain.inventory_use_case.InventoryUseCases
import com.example.farmerpro.domain.marketplace_use_case.AddImageToStorage
import com.example.farmerpro.domain.inventory_use_case.UpdateInventory
import com.example.farmerpro.domain.repository.FarmerRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
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
    fun provideItemsRepository(): MarketRepository = MarketRepositoryImpl(
        storage = Firebase.storage,
        itemsRef = Firebase.firestore.collection("items")
    )
    @Provides
    fun provideUseCases(
        repo: MarketRepository
    ) = UseCases(
        getItems = GetItems(repo),
        addItem = AddItem(repo),
        deleteItem = DeleteItem(repo),
        addImageToStorage = AddImageToStorage(repo)
    )

    @Provides
    fun provideInventoryRepository(): FarmerRepository = FarmerRepositoryImpl(Firebase.firestore.collection("farmers"))

    @Provides
    fun provideInventoryUseCases(
        repo: FarmerRepository
    ) = InventoryUseCases(
        getItems = GetInventoryByFarmer(repo),
        addItem = AddOrUpdateInventory(repo),
        updateInventory = UpdateInventory(repo)
    )

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

}