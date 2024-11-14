package com.example.librarymanagementsystem.data.repository.book

import com.example.librarymanagementsystem.data.model.Book

class BookDaoMock : BookDao {
    private val dataList = mutableListOf<Book>()

    override fun getAll(): List<Book> = dataList

    override fun insert(book: Book) {
        dataList.add(book)
    }

    override fun update(book: Book) {
        dataList.replaceAll { if (it.id == book.id) book else it }
    }

    override fun findById(bookId: Int?): Book? {
        return dataList.find { it.id == bookId }
    }

    override fun deleteById(bookId: Int?) {
        dataList.removeIf { it.id == bookId }
    }

    override fun truncate() {
        dataList.clear()
    }
}