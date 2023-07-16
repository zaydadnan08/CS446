package com.example.farmerpro.data

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.repository.DeleteItemResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarketRepositoryImpl @Inject constructor(
    private val itemsRef: CollectionReference
): MarketRepository {
    override fun getItemsFromFirestore() = callbackFlow {
        val snapshotListener = itemsRef.orderBy("product_name").addSnapshotListener { snapshot, e ->
            val ItemsResponse = if (snapshot != null) {
                val items = snapshot.toObjects(MarketplaceItem::class.java)
                Success(items)
            } else {
                Failure(e)
            }
            trySend(ItemsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addItemToFirestore(product_name: String, seller: String): AddItemResponse = try {
        val id = itemsRef.document().id
        val item = MarketplaceItem(
            id = id,
            product_name = product_name,
            seller = seller
        )
        itemsRef.document(id).set(item).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteItemFromFirestore(itemId: String): DeleteItemResponse = try {
        itemsRef.document(itemId).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}