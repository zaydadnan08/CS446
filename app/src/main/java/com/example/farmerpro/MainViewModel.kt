package com.example.farmerpro

import androidx.lifecycle.ViewModel
import com.example.farmerpro.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    fun isUserLoggedIn(): Boolean {
        return repository.currentUser != null
    }
    var email = repository.currentUser?.email ?: ""
}