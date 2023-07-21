package com.example.farmerpro.domain.request_use_case

import com.example.farmerpro.domain.repository.RequestRepository

class DeleteRequest(
    private val repo: RequestRepository
) {
    suspend operator fun invoke(itemId: String) = repo.deleteRequest(itemId)
}