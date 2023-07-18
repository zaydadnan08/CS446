package com.example.farmerpro.ui.home.markets

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.Response.Loading
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.ItemResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.marketplace_use_case.UseCases
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.domain.repository.UserResponse
import com.example.farmerpro.types.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val useCases: UseCases,
    private val repository: AuthRepository
): ViewModel() {
    var itemsResponse by mutableStateOf<ItemResponse>(Loading)
    var addImageToStorageResponse by mutableStateOf<CameraResponse<Uri>>(CameraResponse.Success(null))
        private set

    private val _user = MutableStateFlow<Response<User>>(Loading)
    val user: StateFlow<Response<User>> = _user
    var userId = repository.currentUser?.uid
    private val _currentUser = MutableStateFlow<User?>(null)
    private val currentUser: StateFlow<User?> = _currentUser
    val downloadUrl = mutableStateOf("")

    var addItemResponse by mutableStateOf<AddItemResponse>(Success(false))
        private set
    var deleteItemResponse by mutableStateOf<DeleteItemResponse>(Success(false))
        private set

    init {
        getItems()
        viewModelScope.launch {
            if(userId == null){
                userId = ""
            }
            _user.value = repository.getUserFromFirestore(userId!!)
            user.collect { response ->
                when (response) {
                    is Response.Loading -> {
                        // Show loading indicator
                    }
                    is Response.Success -> {
                        _currentUser.value = response.data
                    }
                    is Response.Failure -> {
                        // Show error message
                    }
                }
            }
        }
    }
    private fun getItems() = viewModelScope.launch {
        useCases.getItems().collect { response ->
            itemsResponse = response
        }
    }

    fun addItem(product_name: String, price: String, description: String, location: String) = viewModelScope.launch {
        addItemResponse = Loading
        addItemResponse = useCases.addItem(product_name,
            currentUser.value?.name ?: "", price, description, location, currentUser.value?.contactNumber ?: "", downloadUrl.value)
        useCases.getItems().collect { response ->
            itemsResponse = response
        }
    }

    fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = CameraResponse.Loading
        addImageToStorageResponse = useCases.addImageToStorage(imageUri, (0..100).random().toString())
    }

    fun setDownloadUrl(url: String){
        downloadUrl.value = url;
    }

    fun deleteItem(ItemId: String) = viewModelScope.launch {
        deleteItemResponse = Loading
        deleteItemResponse = useCases.deleteItem(ItemId)
    }
}