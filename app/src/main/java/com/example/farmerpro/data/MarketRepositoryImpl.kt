package com.example.farmerpro.data

import android.net.Uri
import android.util.Log
import com.example.farmerpro.core.Constants
import com.example.farmerpro.domain.model.CameraResponse
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddImageToStorageResponse
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.MarketRepository
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarketRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val itemsRef: CollectionReference,
) : MarketRepository {
    override fun getItemsFromFirestore() = callbackFlow {
        val snapshotListener = itemsRef.orderBy("product_name").addSnapshotListener { snapshot, e ->
            val itemsResponse = if (snapshot != null) {
                val items = snapshot.toObjects(MarketplaceItem::class.java)
                Success(items)
            } else {
                Failure(e)
            }
            trySend(itemsResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addItemToFirestore(
        product_name: String,
        uid: String,
        seller: String,
        price: String,
        description: String,
        location: String,
        contact_number: String,
        imageUrl: String
    ): AddItemResponse = try {
        val id = itemsRef.document().id
        val item = MarketplaceItem(
            id = id,
            uid = uid,
            product_name = product_name,
            seller = seller,
            price = price,
            location = location,
            description= description,
            contact_number = contact_number,
            imageUrl = imageUrl,
            rating = null,
            numberOfRatings = 0
        )
        itemsRef.document(id).set(item).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun addImageToFirebaseStorage(
        imageUri: Uri, fileName: String
    ): AddImageToStorageResponse {
        return try {
            val downloadUrl =
                storage.reference.child(Constants.IMAGES).child("$fileName.jpg").putFile(imageUri)
                    .await().storage.downloadUrl.await()
            CameraResponse.Success(downloadUrl)
        } catch (e: Exception) {
            CameraResponse.Failure(e)
        }
    }

    override suspend fun deleteItemFromFirestore(itemId: String): DeleteItemResponse = try {
        itemsRef.document(itemId).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun updateRating(itemId: String,
                                      item: MarketplaceItem,
                                      rating: Double): Response<Boolean> = try {
        val ratingValue = if(item.rating == null){
            rating
        } else {
            (rating + (item.rating!! * item.numberOfRatings)) / (item.numberOfRatings + 1)
        }
        itemsRef.document(itemId).update("rating", ratingValue)
        itemsRef.document(itemId).update("numberOfRatings", item.numberOfRatings + 1)
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}