package com.example.farmerpro.ui.home.books

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.farmerpro.components.TopBar
import com.example.farmerpro.ui.home.books.components.AddBook
import com.example.farmerpro.ui.home.books.components.AddBookAlertDialog
import com.example.farmerpro.ui.home.books.components.AddBookFloatingActionButton
import com.example.farmerpro.ui.home.books.components.Books
import com.example.farmerpro.ui.home.books.components.BooksContent
import com.example.farmerpro.ui.home.books.components.DeleteBook

@Composable
fun BooksScreen(
    viewModel: BooksViewModel = hiltViewModel()
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
            AddBookFloatingActionButton(
                openDialog = {
                    openDialog = true
                }
            )
        }
    )
    AddBook()
    DeleteBook()
}