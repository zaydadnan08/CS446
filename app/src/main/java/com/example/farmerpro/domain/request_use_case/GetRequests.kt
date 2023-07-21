package com.example.farmerpro.domain.request_use_case

import com.example.farmerpro.domain.repository.RequestRepository

class GetRequests(
    private val repo: RequestRepository
) {
    operator fun invoke() = repo.getRequestsFromFirestore()
}