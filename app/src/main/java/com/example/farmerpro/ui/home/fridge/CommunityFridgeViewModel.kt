package com.example.farmerpro.ui.home.fridge


import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.BaseViewModel
import com.example.farmerpro.domain.fridge_use_case.FridgeUseCases
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AddFridgeResponse
import com.example.farmerpro.domain.repository.AddRequestResponse
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.domain.repository.DeleteFridgeResponse
import com.example.farmerpro.domain.repository.DeleteRequestResponse
import com.example.farmerpro.domain.repository.FridgeResponse
import com.example.farmerpro.domain.repository.RequestResponse
import com.example.farmerpro.domain.request_use_case.RequestUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityFridgeViewModel @Inject constructor(
    private val fridgeUseCases: FridgeUseCases,
    private val requestUseCases: RequestUseCases,
    repository: AuthRepository
) : BaseViewModel(repository) {

    var requestResponse by mutableStateOf<RequestResponse>(Response.Loading)
    var addRequestResponse by mutableStateOf<AddRequestResponse>(Response.Success(false))
        private set

    var fridgeResponse by mutableStateOf<FridgeResponse>(Response.Loading)
    var addFridgeResponse by mutableStateOf<AddFridgeResponse>(Response.Success(false))
        private set
    var deleteFridgeResponse by mutableStateOf<DeleteFridgeResponse>(Response.Success(false))
        private set

    var updateFridgeResponse by mutableStateOf<Response<Boolean>>(Response.Success(false))
        private set

    var deleteRequestResponse by mutableStateOf<DeleteRequestResponse>(Response.Success(false))
        private set
    var addImageToStorageResponse by mutableStateOf<CameraResponse<Uri>>(CameraResponse.Success(null))
        private set

    init {
        getRequests()
        getFridges()
    }

    private fun getRequests() = viewModelScope.launch {
        requestUseCases.getRequests().collect { response ->
            requestResponse = response
        }
    }

    fun addRequest(
        name: String,
        description: String,
        amount: String,
        location: String,
        fridgeName: String
    ) =
        viewModelScope.launch {
            addRequestResponse = Response.Loading
            addRequestResponse = requestUseCases.addRequest(
                name,
                description,
                amount,
                location,
                currentUser.value?.contactNumber ?: "",
                fridgeName,
                userId.value ?: ""
            )
            requestUseCases.getRequests().collect { response ->
                requestResponse = response
            }
        }

    fun deleteRequest(ItemId: String) = viewModelScope.launch {
        deleteRequestResponse = Response.Loading
        deleteRequestResponse = requestUseCases.deleteRequest(ItemId)
    }

    override fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        addImageToStorageResponse = CameraResponse.Loading
        addImageToStorageResponse =
            fridgeUseCases.addImageToStorage(imageUri, (0..100).random().toString())
    }

    fun addFridge(
        name: String,
        location: String,
        fridgeinventory: String
    ) =
        viewModelScope.launch {
            addFridgeResponse = Response.Loading
            addFridgeResponse = fridgeUseCases.addFridge(
                name,
                userId.value ?: "",
                location,
                fridgeinventory,
                currentUser.value?.contactNumber ?: "",
                downloadUrl.value
            )
            fridgeUseCases.getFridges().collect { response ->
                fridgeResponse = response
            }
        }

    fun deleteFridge(ItemId: String) = viewModelScope.launch {
        deleteFridgeResponse = Response.Loading
        deleteFridgeResponse = fridgeUseCases.deleteFridge(ItemId)
    }

    private fun getFridges() = viewModelScope.launch {
        fridgeUseCases.getFridges().collect() { response ->
            fridgeResponse = response
        }
        requestUseCases.getRequests().collect { response ->
            requestResponse = response
        }
    }
    fun updateFridge(ItemId: String, fridgeInventory: String) = viewModelScope.launch {
        updateFridgeResponse = Response.Loading
        updateFridgeResponse = fridgeUseCases.updateFridge(ItemId, fridgeInventory)
    }
}