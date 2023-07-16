package com.example.farmerpro.domain.repository

import kotlinx.coroutines.flow.Flow
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response

typealias Items = List<MarketplaceItem>
typealias ItemResponse = Response<Items>
typealias AddItemResponse = Response<Boolean>
typealias DeleteItemResponse = Response<Boolean>

interface MarketRepository {
    fun getItemsFromFirestore(): Flow<ItemResponse>

    suspend fun addItemToFirestore(product_name: String, seller: String, price: String, location: String, contact_number: String): AddItemResponse

    suspend fun deleteItemFromFirestore(itemId: String): DeleteItemResponse
}