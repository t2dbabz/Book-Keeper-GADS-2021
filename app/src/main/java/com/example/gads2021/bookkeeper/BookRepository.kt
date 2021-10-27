package com.example.gads2021.bookkeeper

class BookRepository(private val db: BookRoomDatabase) {
    fun getAllBooks() = db.bookDao().getAllBooks()

    suspend fun insertBook(book: Book) {
        db.bookDao().insertBook(book)
    }

    suspend fun updateBook(book: Book) {
        db.bookDao().updateBook(book)
    }

    suspend fun deleteBook(book: Book) {
        db.bookDao().deleteBook(book)
    }
}