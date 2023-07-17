package com.example.farmerpro.domain.repository

import android.net.Uri
import com.example.farmerpro.domain.model.CameraResponse

typealias AddImageToStorageResponse = CameraResponse<Uri>
typealias AddImageUrlToFirestoreResponse = CameraResponse<Boolean>
typealias GetImageUrlFromFirestoreResponse = CameraResponse<String>

interface ProfileImageRepository {
    suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse

    suspend fun addImageUrlToFirestore(downloadUrl: Uri): AddImageUrlToFirestoreResponse

    suspend fun getImageUrlFromFirestore(): GetImageUrlFromFirestoreResponse
}