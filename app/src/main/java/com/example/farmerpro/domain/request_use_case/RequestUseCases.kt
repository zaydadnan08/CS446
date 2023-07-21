package com.example.farmerpro.domain.request_use_case

data class RequestUseCases(
    val getRequests: GetRequests,
    val addRequest: AddRequest,
    val deleteRequest: DeleteRequest,
)