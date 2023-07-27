package com.example.farmerpro

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.domain.model.Response
import com.example.farmerpro.domain.repository.AuthRepository
import com.example.farmerpro.types.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel (
    protected val repository: AuthRepository
) : ViewModel() {
    protected val _user = MutableStateFlow<Response<User>>(Response.Loading)
    val user: StateFlow<Response<User>> = _user
    protected val _currentUser = MutableStateFlow<User?>(null)
    protected val currentUser: StateFlow<User?> = _currentUser
    val downloadUrl = mutableStateOf("")
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

    open fun addImageToStorage(imageUri: Uri) = viewModelScope.launch {
        // This method can be overridden in child classes if needed
    }

    fun setDownloadUrl(url: String) {
        downloadUrl.value = url
    }

    fun signOut(): () -> Unit = {
        repository.signOut()
    }
}
