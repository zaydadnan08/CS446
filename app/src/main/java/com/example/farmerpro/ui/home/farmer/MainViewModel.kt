package com.example.farmerpro.ui.home.farmer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    var state by mutableStateOf(farmscreenstate())
        private set

    fun changeTextValue(text:String){
        viewModelScope.launch {
            state = state.copy(
                text = text
            )
        }
    }
}