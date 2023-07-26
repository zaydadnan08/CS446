package com.example.farmerpro.dependencyInjection

import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.data.AuthRepositoryImpl
import com.example.farmerpro.data.FarmerRepositoryImpl
import com.example.farmerpro.data.FridgeRepositoryImpl
import com.example.farmerpro.data.MarketRepositoryImpl
import com.example.farmerpro.data.RequestRepositoryImpl
import com.example.farmerpro.domain.fridge_use_case.AddFridge
import com.example.farmerpro.domain.fridge_use_case.DeleteFridge
import com.example.farmerpro.domain.fridge_use_case.FridgeUseCases
import com.example.farmerpro.domain.fridge_use_case.GetFridges
import com.example.farmerpro.domain.inventory_use_case.AddOrUpdateInventory
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.inventory_use_case.GetInventoryByFarmer
import com.example.farmerpro.domain.inventory_use_case.InventoryUseCases
import com.example.farmerpro.domain.inventory_use_case.TrackSale
import com.example.farmerpro.domain.inventory_use_case.UpdateInventory
import com.example.farmerpro.domain.marketplace_use_case.AddImageToStorage
import com.example.farmerpro.domain.marketplace_use_case.AddItem
import com.example.farmerpro.domain.marketplace_use_case.DeleteItem
import com.example.farmerpro.domain.marketplace_use_case.GetItems
import com.example.farmerpro.domain.marketplace_use_case.MarketUseCases
import com.example.farmerpro.domain.marketplace_use_case.UpdateItem
import com.example.farmerpro.domain.repository.FarmerRepository
import com.example.farmerpro.domain.repository.FridgeRepository
import com.example.farmerpro.domain.repository.RequestRepository
import com.example.farmerpro.domain.request_use_case.AddRequest
import com.example.farmerpro.domain.request_use_case.DeleteRequest
import com.example.farmerpro.domain.request_use_case.GetRequests
import com.example.farmerpro.domain.request_use_case.RequestUseCases
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
        storage = Firebase.storage, itemsRef = Firebase.firestore.collection("items")
    )

    @Provides
    fun provideFridgeRepository(): FridgeRepository = FridgeRepositoryImpl(
        storage = Firebase.storage, itemsRef = Firebase.firestore.collection("fridge")
    )

    @Provides
    fun provideInventoryRepository(): FarmerRepository =
        FarmerRepositoryImpl(Firebase.firestore.collection("farmers"))

    @Provides
    fun provideRequestRepository(): RequestRepository =
        RequestRepositoryImpl(Firebase.firestore.collection("requests"))

    @Provides
    fun provideInventoryUseCases(
        repo: FarmerRepository
    ) = InventoryUseCases(
        getItems = GetInventoryByFarmer(repo),
        addItem = AddOrUpdateInventory(repo),
        updateInventory = UpdateInventory(repo),
        trackSale = TrackSale(repo)
    )

    @Provides
    fun provideRequestUseCases(
        repo: RequestRepository
    ) = RequestUseCases (
        getRequests = GetRequests(repo),
        addRequest = AddRequest(repo),
        deleteRequest = DeleteRequest(repo),
    )

    @Provides
    fun provideFridgeUseCases(
        repo: FridgeRepository
    ) = FridgeUseCases (
        getFridges = GetFridges(repo),
        addFridge = AddFridge(repo),
        deleteFridge = DeleteFridge(repo),
        addImageToStorage = com.example.farmerpro.domain.fridge_use_case.AddImageToStorage(repo)
    )

    @Provides
    fun provideUseCases(
        repo: MarketRepository
    ) = MarketUseCases (
        getItems = GetItems(repo),
        addItem = AddItem(repo),
        deleteItem = DeleteItem(repo),
        addImageToStorage = AddImageToStorage(repo),
        updateItem = UpdateItem(repo)
    )

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

}