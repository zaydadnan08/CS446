package com.example.farmerpro.ui.home.profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.CameraResponse.*

@Composable
fun GetImageFromDatabase(
    viewModel: ProfileViewModel = hiltViewModel(),
    createProfileImageContent: @Composable (imageUrl: String) -> Unit
) {
    when(val getImageFromDatabaseResponse = viewModel.getImageFromDatabaseResponse) {
        is Loading -> ProgressBar()
        is Success -> getImageFromDatabaseResponse.data?.let { imageUrl ->
            createProfileImageContent(imageUrl)
        }
        is Failure -> print(getImageFromDatabaseResponse.e)
    }
}