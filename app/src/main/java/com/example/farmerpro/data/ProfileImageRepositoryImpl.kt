package com.example.farmerpro.data

import android.net.Uri
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import com.example.farmerpro.core.Constants.CREATED_AT
import com.example.farmerpro.core.Constants.IMAGES
import com.example.farmerpro.core.Constants.PROFILE_IMAGE_NAME
import com.example.farmerpro.core.Constants.UID
import com.example.farmerpro.core.Constants.URL
import com.example.farmerpro.domain.model.CameraResponse.Failure
import com.example.farmerpro.domain.model.CameraResponse.Success
import com.example.farmerpro.domain.repository.AddImageToStorageResponse
import com.example.farmerpro.domain.repository.AddImageUrlToFirestoreResponse
import com.example.farmerpro.domain.repository.GetImageUrlFromFirestoreResponse
import com.example.farmerpro.domain.repository.ProfileImageRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileImageRepositoryImpl @Inject constructor(
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
) : ProfileImageRepository {
    override suspend fun addImageToFirebaseStorage(imageUri: Uri): AddImageToStorageResponse {
        return try {
            val downloadUrl = storage.reference.child(IMAGES).child(PROFILE_IMAGE_NAME)
                .putFile(imageUri).await()
                .storage.downloadUrl.await()
            Success(downloadUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun addImageUrlToFirestore(downloadUrl: Uri): AddImageUrlToFirestoreResponse {
        return try {
            db.collection(IMAGES).document(UID).set(mapOf(
                URL to downloadUrl,
                CREATED_AT to FieldValue.serverTimestamp()
            )).await()
            Success(true)
        } catch (e: Exception) {
            Failure(e)
        }
    }

    override suspend fun getImageUrlFromFirestore(): GetImageUrlFromFirestoreResponse {
        return try {
            var imageUrl = db.collection(IMAGES).document(UID).get().await().getString(URL)
            Success(imageUrl)
        } catch (e: Exception) {
            Failure(e)
        }
    }
}