package com.example.farmerpro.domain.repository

import android.net.Uri
import com.example.farmerpro.domain.model.CameraResponse
import kotlinx.coroutines.flow.Flow
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response

typealias Items = List<MarketplaceItem>
typealias ItemResponse = Response<Items>
typealias AddItemResponse = Response<Boolean>
typealias DeleteItemResponse = Response<Boolean>
typealias AddImageToStorageResponse = CameraResponse<Uri>

interface MarketRepository {
    fun getItemsFromFirestore(): Flow<ItemResponse>
    suspend fun addItemToFirestore(
        product_name: String,
        seller: String,
        price: String,
        description: String,
        location: String,
        contact_number: String,
        imageUrl: String
    ): AddItemResponse

    suspend fun deleteItemFromFirestore(itemId: String): DeleteItemResponse
    suspend fun addImageToFirebaseStorage(
        imageUri: Uri, fileName: String
    ): AddImageToStorageResponse


}