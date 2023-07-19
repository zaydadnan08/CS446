package com.example.farmerpro.domain.repository

import com.example.farmerpro.domain.model.FridgeRequest
import kotlinx.coroutines.flow.Flow
import com.example.farmerpro.domain.model.Response

typealias FridgeRequests = List<FridgeRequest>
typealias RequestResponse = Response<FridgeRequests>
typealias AddRequestResponse = Response<Boolean>
typealias DeleteRequestResponse = Response<Boolean>

interface RequestRepository {
    fun getRequestsFromFirestore(): Flow<RequestResponse>

    suspend fun addRequestToFirestore(
        product_name: String,
        description: String,
        amount: String,
        location: String,
        contact_number: String,
        fridge_name: String,
        uid: String,
        ): AddRequestResponse

    suspend fun deleteRequest(itemId: String): DeleteRequestResponse
}