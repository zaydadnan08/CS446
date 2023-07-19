package com.example.farmerpro.domain.fridge_use_case


import android.net.Uri
import com.example.farmerpro.domain.repository.FridgeRepository

class AddImageToStorage(
    private val repo: FridgeRepository
) {
    suspend operator fun invoke(
        imageUri: Uri, fileName: String
    ) = repo.addImageToFirebaseStorage(imageUri, fileName)
}