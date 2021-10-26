package com.example.gads2021.bookkeeper

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase(): RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: BookRoomDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                BookRoomDatabase::class.java,
                "book_database.db"
            )
                .build()

    }

}