package com.example.gads2021.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Query("SELECT * FROM books WHERE book_name LIKE :searchString OR author LIKE :searchString")
    fun getBooksByBookOrAuthor(searchString: String): LiveData<List<Book>>

    @Insert
    suspend fun insertBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}