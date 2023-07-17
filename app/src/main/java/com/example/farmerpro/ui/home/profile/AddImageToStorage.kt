package com.example.farmerpro.ui.home.profile

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.CameraResponse.*

@Composable
fun AddImageToStorage(
    viewModel: ProfileViewModel = hiltViewModel(),
    addImageToDatabase: (downloadUrl: Uri) -> Unit
) {
    when(val addImageToStorageResponse = viewModel.addImageToStorageResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToStorageResponse.data?.let { downloadUrl ->
            LaunchedEffect(downloadUrl) {
                addImageToDatabase(downloadUrl)
            }
        }
        is Failure -> print(addImageToStorageResponse.e)
    }
}