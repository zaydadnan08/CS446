package com.example.farmerpro.data

import com.example.farmerpro.domain.model.FridgeRequest
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.domain.model.Response.Failure
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddRequestResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.repository.RequestRepository
import com.example.farmerpro.domain.repository.RequestResponse
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestRepositoryImpl @Inject constructor(
    private val requestRef: CollectionReference,
) : RequestRepository {
    override fun getRequestsFromFirestore(): Flow<RequestResponse> = callbackFlow {
        val snapshotListener = requestRef.orderBy("product_name").addSnapshotListener { snapshot, e ->
            val requestResponse = if (snapshot != null) {
                val items = snapshot.toObjects(FridgeRequest::class.java)
                Success(items)
            } else {
                Failure(e)
            }
            trySend(requestResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun addRequestToFirestore(
        product_name: String,
        description: String,
        amount: String,
        location: String,
        contact_number: String,
        fridge_name: String,
        uid: String
    ): AddRequestResponse  = try {
        val id = requestRef.document().id
        val item = FridgeRequest(
            product_name =  product_name,
            description = description,
            amount = amount,
            location =  location,
            contact_number = contact_number,
            fridge_name = fridge_name,
            uid =  uid
        )
        requestRef.document(id).set(item).await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }

    override suspend fun deleteRequest(itemId: String): DeleteItemResponse = try {
        requestRef.document(itemId).delete().await()
        Success(true)
    } catch (e: Exception) {
        Failure(e)
    }
}