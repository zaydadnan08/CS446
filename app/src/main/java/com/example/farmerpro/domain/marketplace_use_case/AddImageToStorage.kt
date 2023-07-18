package com.example.farmerpro.domain.marketplace_use_case

import android.net.Uri
import com.example.farmerpro.domain.repository.MarketRepository

class AddImageToStorage(
    private val repo: MarketRepository
) {
    suspend operator fun invoke(
        imageUri: Uri, fileName: String
    ) = repo.addImageToFirebaseStorage(imageUri, fileName)
}