package com.example.farmerpro.ui.home.fridge


import androidx.lifecycle.ViewModel
import com.example.farmerpro.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommunityFridgeViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun signOut() = { repository.signOut() }
}