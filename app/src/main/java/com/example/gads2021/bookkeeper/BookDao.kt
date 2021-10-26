package com.example.gads2021.bookkeeper

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert
    suspend fun insertBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)
}