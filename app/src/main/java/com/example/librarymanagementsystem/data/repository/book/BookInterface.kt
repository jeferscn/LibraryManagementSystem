package com.example.librarymanagementsystem.data.repository.book

import com.example.librarymanagementsystem.data.model.Book

interface BookInterface  {
    fun truncate()
    fun getList(): List<Book>
    fun insert(book: Book)
    fun update(book: Book)
    fun delete(bookId: Int?): Boolean
    fun find(bookId: Int?): Book?
    fun getMockData(): Book
}