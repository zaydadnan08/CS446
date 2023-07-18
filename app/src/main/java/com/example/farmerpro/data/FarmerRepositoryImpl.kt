package com.example.farmerpro.data

import com.example.farmerpro.domain.model.InventoryItem
import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.FarmerRepository
import com.example.farmerpro.domain.repository.InventoryResponse
import com.google.firebase.firestore.FieldValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FarmerRepositoryImpl @Inject constructor(
    private val inventoryRef: CollectionReference
): FarmerRepository {
    override fun getItemsFromFirestore(farmerID: String): Flow<InventoryResponse> = callbackFlow {
        val snapshotListener = inventoryRef.document(farmerID).addSnapshotListener { snapshot, e ->
            val ItemsResponse = if (snapshot != null) {
                val items = snapshot.toObject(InventoryItems::class.java)
                Success(items)
            } else {
                Failure(e)
            }
            trySend(ItemsResponse as InventoryResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
}

    override suspend fun addItemToFirestore(item: InventoryItem, farmerID: String): Response<Boolean> = try {
        inventoryRef.document(farmerID).update("inventory", FieldValue.arrayUnion(item)).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun updateInventoryItems(newInventoryItems: InventoryItems, farmerID: String): Response<Boolean> = try {
        inventoryRef.document(farmerID).set(newInventoryItems).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}