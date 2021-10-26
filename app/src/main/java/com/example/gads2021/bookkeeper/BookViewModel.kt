package com.example.gads2021.bookkeeper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(private val bookRepository: BookRepository): ViewModel() {

    fun getAllBooks() = bookRepository.getAllBooks()

    fun insertBook(book: Book) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
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