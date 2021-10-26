package com.example.gads2021.bookkeeper

class BookRepository(private val bookDao: BookDao) {
    fun getAllBooks() = bookDao.getAllBooks()

    suspend fun insertBook(book: Book) {
        bookDao.insertBook(book)
    }

    suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}