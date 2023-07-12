package com.example.farmerpro.ui.home.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.ui.home.books.BooksViewModel

@Composable
fun DeleteBook(
    viewModel: BooksViewModel = hiltViewModel()
) {
    when(val deleteBookResponse = viewModel.deleteBookResponse) {
        is Loading -> ProgressBar()
        is Success -> Unit
        is Failure -> print(deleteBookResponse.e)
    }
}