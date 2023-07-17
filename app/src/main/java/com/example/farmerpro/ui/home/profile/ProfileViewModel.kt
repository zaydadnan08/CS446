package com.example.farmerpro.ui.home.profile

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.domain.repository.ProfileImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.CameraResponse.Loading
import com.example.farmerpro.domain.model.CameraResponse.Success
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repo: ProfileImageRepository
): ViewModel() {
    var addImageToStorageResponse by mutableStateOf<CameraResponse<Uri>>(Success(null))
        private set
    var addImageToDatabaseResponse by mutableStateOf<CameraResponse<Boolean>>(Success(null))
        private set
    var getImageFromDatabaseResponse by mutableStateOf<CameraResponse<String>>(Success(null))
        private set

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = Loading
        addImageToStorageResponse = repo.addImageToFirebaseStorage(imageUri)
    }

    fun addImageToDatabase(downloadUrl: Uri) = viewModelScope.launch {
        addImageToDatabaseResponse = Loading
        addImageToDatabaseResponse = repo.addImageUrlToFirestore(downloadUrl)
    }

    fun getImageFromDatabase() = viewModelScope.launch {
        getImageFromDatabaseResponse = Loading
        getImageFromDatabaseResponse = repo.getImageUrlFromFirestore()
    }
}