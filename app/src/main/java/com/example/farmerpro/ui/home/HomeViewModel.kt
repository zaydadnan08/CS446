package com.example.farmerpro.ui.home

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.farmerpro.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.Response.Loading
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddItemResponse
import com.example.farmerpro.domain.repository.ItemResponse
import com.example.farmerpro.domain.repository.DeleteItemResponse
import com.example.farmerpro.domain.marketplace_use_case.MarketUseCases
import com.example.farmerpro.domain.model.CameraResponse
import com.example.farmerpro.domain.model.MarketplaceItem
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.types.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AuthRepository) : ViewModel() {

    private val _user = MutableStateFlow<Response<User>>(Loading)
    val user: StateFlow<Response<User>> = _user
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser
    var userId = mutableStateOf(repository.currentUser?.uid)

    init {
        viewModelScope.launch {
            if (userId.value == null) {
                userId.value = ""
            }
            _user.value = repository.getUserFromFirestore(userId.value!!)
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
}