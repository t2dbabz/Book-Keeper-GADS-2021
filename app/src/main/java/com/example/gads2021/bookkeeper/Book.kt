package com.example.gads2021.bookkeeper

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey
    val id: String,

    val author: String,

    @ColumnInfo(name = "book_name")
    val bookName: String
)
