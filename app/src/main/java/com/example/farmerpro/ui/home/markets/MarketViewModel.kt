package com.example.farmerpro.ui.home.markets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.farmerpro.domain.model.Response.Loading
import com.example.farmerpro.domain.model.Response.Success
import com.example.farmerpro.domain.repository.AddBookResponse
import com.example.farmerpro.domain.repository.BooksResponse
import com.example.farmerpro.domain.repository.DeleteBookResponse
import com.example.farmerpro.domain.marketplace_use_case.UseCases
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    private val useCases: UseCases
): ViewModel() {
    var itemsResponse by mutableStateOf<BooksResponse>(Loading)
        private set
    var addItemResponse by mutableStateOf<AddBookResponse>(Success(false))
        private set
    var deleteItemResponse by mutableStateOf<DeleteBookResponse>(Success(false))
        private set

    init {
        getBooks()
    }

    private fun getBooks() = viewModelScope.launch {
        useCases.getBooks().collect { response ->
            itemsResponse = response
        }
    }

    fun addBook(title: String, author: String) = viewModelScope.launch {
        addItemResponse = Loading
        addItemResponse = useCases.addBook(title, author)
    }

    fun deleteBook(bookId: String) = viewModelScope.launch {
        deleteItemResponse = Loading
        deleteItemResponse = useCases.deleteBook(bookId)
    }
}