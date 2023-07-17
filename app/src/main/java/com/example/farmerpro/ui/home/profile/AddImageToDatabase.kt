package com.example.farmerpro.ui.home.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.CameraResponse.*

@Composable
fun AddImageToDatabase(
    viewModel: ProfileViewModel = hiltViewModel(),
    showSnackBar: (isImageAddedToDatabase: Boolean) -> Unit
) {
    when(val addImageToDatabaseResponse = viewModel.addImageToDatabaseResponse) {
        is Loading -> ProgressBar()
        is Success -> addImageToDatabaseResponse.data?.let { isImageAddedToDatabase ->
            LaunchedEffect(isImageAddedToDatabase) {
                showSnackBar(isImageAddedToDatabase)
            }
        }
        is Failure -> print(addImageToDatabaseResponse.e)
    }
}