package com.example.farmerpro.data

import android.net.Uri
import com.example.farmerpro.core.Constants
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.FridgeItem
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AddFridgeResponse
import com.example.farmerpro.domain.repository.AddImageToStorageResponse
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.DeleteFridgeResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.repository.FridgeRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FridgeRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val itemsRef: CollectionReference,
) : FridgeRepository
{
    override fun getFridgesFromFirestore() = callbackFlow {
        val snapshotListener = itemsRef.orderBy("fridge_name").addSnapshotListener { snapshot, e ->
            val fridgeResponse = if (snapshot != null) {
                val items = snapshot.toObjects(FridgeItem::class.java)
                Response.Success(items)
            } else {
                Response.Failure(e)
            }
            trySend(fridgeResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }
    override suspend fun addFridgeToFirestore(
        fridge_name: String,
        uid: String,
        location: String,
        fridgeinventory: String,
        contact_number: String,
        imageUrl: String
    ): AddFridgeResponse = try {
        val id = itemsRef.document().id
        val item = FridgeItem(
            id = id,
            uid = uid,
            fridge_name = fridge_name,
            location = location,
            fridgeinventory = fridgeinventory,
            contact_number = contact_number,
            imageUrl = imageUrl
        )
        itemsRef.document(id).set(item).await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
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
    override suspend fun deleteFridgeFromFirestore(itemId: String): DeleteFridgeResponse = try {
        itemsRef.document(itemId).delete().await()
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

    override suspend fun updateFridgeFromFirestore(itemId: String, fridgeInventory: String): Response<Boolean> = try {
        itemsRef.document(itemId).update("fridgeinventory", fridgeInventory)
        Response.Success(true)
    } catch (e: Exception) {
        Response.Failure(e)
    }

}

