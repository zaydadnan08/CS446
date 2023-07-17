package com.example.farmerpro.data

import com.example.farmerpro.domain.model.InventoryItems
import com.example.farmerpro.domain.model.Response
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.FarmerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FarmerRepositoryImpl @Inject constructor(
    private val itemsRef: CollectionReference
): FarmerRepository {
    override fun getItemsFromFirestore(farmerID: String): Flow<InventoryItems> = callbackFlow {
        itemsRef.document(farmerID).get().addOnCompleteListener { doc ->
            if (doc.isSuccessful) {
                val items = doc.result.toObject(InventoryItems::class.java)
                if (items != null) {
                    Success(items)
                    trySend(items)
                }
            }
        }
    }

    override suspend fun addItemToFirestore(inventory: InventoryItems, farmerID: String): Response<Boolean> = try {
        itemsRef.document(farmerID).set(inventory).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteItemFromFirestore(farmerID: String): Response<Boolean> = try {
        itemsRef.document(farmerID).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}