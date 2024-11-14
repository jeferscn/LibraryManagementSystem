package com.example.librarymanagementsystem.data.repository.book

import com.example.librarymanagementsystem.data.model.Book

interface BookInterface  {
    suspend fun truncate()
    suspend fun getList(): List<Book>
    suspend fun insert(book: Book)
    suspend fun update(book: Book)
    suspend fun delete(bookId: Int?): Boolean
    suspend fun find(bookId: Int?): Book?
    suspend fun getMockData(): Book
}