package com.example.librarymanagementsystem.data.repository.borrow

import com.example.librarymanagementsystem.data.model.Borrow

/**
 * Mock repository for borrows
 */
object BorrowRepository: BorrowInterface {
    private val dataList = mutableListOf<Borrow>()

    override fun truncate() {
        dataList.clear()
    }

    override fun getList(): List<Borrow> = dataList

    override fun insert(item: Borrow) {
        item.id = dataList.size + 1

        if ((item.bookId ?: 0) <= 0) {
            return
        }
        if ((item.userId ?: 0) <= 0) {
            return
        }

        dataList.add(item)
    }

    override fun update(item: Borrow) {
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

    override fun delete(itemId: Int?) {
        if (itemId == null) {
            return
        }

        dataList.removeIf { it.id == itemId }
    }

    override fun getBorrowsFromUser(userId: Int?): List<Borrow> {
        if ((userId ?: 0) <= 0) {
            return emptyList()
        }

        return dataList.filter { it.userId == userId }
    }

    override fun getBorrowsFromBook(bookId: Int?): List<Borrow> {
        if ((bookId ?: 0) <= 0) {
            return emptyList()
        }

        return dataList.filter { it.bookId == bookId }
    }
}