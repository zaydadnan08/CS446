package com.example.farmerpro.ui.home.fridge


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.farmerpro.data.AuthRepository
import com.example.farmerpro.data.Resource
import com.example.farmerpro.ui.landing.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityFridgeViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun signOut() = { repository.signOut() }
}