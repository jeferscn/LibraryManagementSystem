package com.example.librarymanagementsystem.data.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.book.BookDao
import com.example.librarymanagementsystem.data.repository.borrow.BorrowDao
import com.example.librarymanagementsystem.data.repository.user.UserDao

@Database(version = 3, exportSchema = false, entities = [Book::class, User::class, Borrow::class])
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "lib_management_sys_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME,
                )

                return instance.fallbackToDestructiveMigration().build()
            }
        }
    }

    abstract fun bookDao(): BookDao

    abstract fun userDao(): UserDao

    abstract fun borrowDao(): BorrowDao
}