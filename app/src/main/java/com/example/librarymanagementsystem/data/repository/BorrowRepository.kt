package com.example.librarymanagementsystem.data.repository

import com.example.librarymanagementsystem.data.model.Borrow

/**
 * Mock repository for borrows
 */
object BorrowRepository {
    private val dataList = mutableListOf<Borrow>()

    fun truncate() {
        dataList.clear()
    }

    fun getList(): List<Borrow> = dataList

    fun insert(item: Borrow) {
        item.id = dataList.size + 1

        if ((item.bookId ?: 0) <= 0) {
            return
        }
        if ((item.userId ?: 0) <= 0) {
            return
        }

        dataList.add(item)
    }

    fun update(item: Borrow) {
        if (item.id == null) {
            return
        }
        if ((item.bookId ?: 0) <= 0) {
            return
        }
        if ((item.userId ?: 0) <= 0) {
            return
        }

        dataList.replaceAll { if (it.id == item.id) item else it }
    }

    fun delete(itemId: Int?) {
        if (itemId == null) {
            return
        }

        dataList.removeIf { it.id == itemId }
    }

    fun getBorrowsFromUser(userId: Int?): List<Borrow> {
        if ((userId ?: 0) <= 0) {
            return emptyList()
        }

        return dataList.filter { it.userId == userId }
    }

    fun getBorrowsFromBook(bookId: Int?): List<Borrow> {
        if ((bookId ?: 0) <= 0) {
            return emptyList()
        }

        return dataList.filter { it.bookId == bookId }
    }
}