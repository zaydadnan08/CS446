package com.example.farmerpro

import androidx.lifecycle.ViewModel
import com.example.farmerpro.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var getCurrentUser = repository.currentUser
    var email = repository.currentUser?.email ?: ""
}