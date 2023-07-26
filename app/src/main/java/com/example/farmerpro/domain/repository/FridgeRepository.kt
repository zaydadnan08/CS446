package com.example.farmerpro.domain.repository

import android.net.Uri
import com.example.farmerpro.domain.model.FridgeItem
import com.example.farmerpro.domain.model.Response
import kotlinx.coroutines.flow.Flow

typealias Fridges = List<FridgeItem>
typealias FridgeResponse = Response<Fridges>
typealias AddFridgeResponse = Response<Boolean>
typealias DeleteFridgeResponse = Response<Boolean>

interface FridgeRepository {
    fun getFridgesFromFirestore(): Flow<FridgeResponse>

    suspend fun addFridgeToFirestore(
        fridge_name: String,
        uid: String,
        location: String,
        fridgeinventory: String,
        contact_number: String,
        imageUrl: String
    ): AddFridgeResponse

    suspend fun deleteFridgeFromFirestore(itemId: String): DeleteFridgeResponse

    suspend fun addImageToFirebaseStorage(
        imageUri: Uri, fileName: String
    ): AddImageToStorageResponse
}