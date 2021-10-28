package com.example.gads2021.bookkeeper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(private val bookRepository: BookRepository): ViewModel() {

    fun getAllBooks() = bookRepository.getAllBooks()

    fun getBooksByBookNameOrAuthor(searchString: String) = bookRepository.getBooksByBookNameOrAuthor(searchString)

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
        }
    }

    fun updateBook(book: Book) {
        viewModelScope.launch {
            bookRepository.updateBook(book)
        }
    }

    fun deleteBook(book: Book) {
        viewModelScope.launch {
            bookRepository.deleteBook(book)
        }
    }

    init {
        getAllBooks()
    }

}

class BookViewModelFactory(val bookRepository: BookRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookViewModel(bookRepository) as T
    }

}