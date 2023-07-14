package com.example.farmerpro.ui.home.books.components

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.ProgressBar
import com.example.farmerpro.domain.model.Response.*
import com.example.farmerpro.domain.repository.Books
import com.example.farmerpro.ui.home.books.BooksViewModel

@Composable
fun Books(
    viewModel: BooksViewModel = hiltViewModel(),
    booksContent: @Composable (books: Books) -> Unit
) {
    when(val booksResponse = viewModel.booksResponse) {
        is Loading -> ProgressBar()
        is Success -> booksContent(booksResponse.data)
        is Failure -> print(booksResponse.e)
    }
}