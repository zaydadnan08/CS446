package com.example.farmerpro.ui.home.markets

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.TopBar
import com.example.farmerpro.ui.home.markets.components.AddBook
import com.example.farmerpro.ui.home.markets.components.AddBookAlertDialog
import com.example.farmerpro.components.AddFloatingActionButton
import com.example.farmerpro.ui.home.markets.components.Books
import com.example.farmerpro.ui.home.markets.components.BooksContent
import com.example.farmerpro.ui.home.markets.components.DeleteBook

@Composable
fun BooksScreen(
    viewModel: MarketViewModel = hiltViewModel()
) {
    var openDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar()
        },
        content = { padding ->
            Books(
                booksContent = { books ->
                    BooksContent(
                        padding = padding,
                        books = books,
                        deleteBook = { bookId ->
                            viewModel.deleteBook(bookId)
                        }
                    )
                    if (openDialog) {
                        AddBookAlertDialog(
                            closeDialog = {
                                openDialog = false
                            },
                            addBook = { title, author ->
                                viewModel.addBook(title, author)
                            }
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                openDialog = {
                    openDialog = true
                }
            )
        }
    )
    AddBook()
    DeleteBook()
}