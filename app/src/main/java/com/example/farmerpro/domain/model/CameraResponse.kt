package com.example.farmerpro.domain.model

sealed class CameraResponse<out T> {
    object Loading: CameraResponse<Nothing>()

    data class Success<out T>(
        val data: T?
    ): CameraResponse<T>()

    data class Failure(
        val e: Exception?
    ): CameraResponse<Nothing>()
}