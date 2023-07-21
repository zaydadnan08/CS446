package com.example.farmerpro.domain.request_use_case

import com.example.farmerpro.domain.repository.RequestRepository

class AddRequest(
    private val repo: RequestRepository
) {
    suspend operator fun invoke(
        product_name: String,
        description: String,
        amount: String,
        location: String,
        contact_number: String,
        fridge_name: String,
        uid: String,
    ) = repo.addRequestToFirestore(
        product_name,
        description,
        amount,
        location,
        contact_number,
        fridge_name,
        uid,
    )
}