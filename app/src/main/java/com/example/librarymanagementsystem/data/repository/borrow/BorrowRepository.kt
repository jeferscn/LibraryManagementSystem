package com.example.librarymanagementsystem.data.repository.borrow

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.Database.borrows

/**
 * Mock repository for borrows
 */
object BorrowRepository: BorrowInterface {

    override fun truncate() {
        borrows.clear()
    }

    override fun getList(): List<Borrow> = borrows

    override fun insert(item: Borrow) {
        item.id = borrows.size + 1

        if ((item.bookId ?: 0) <= 0) {
            return
        }
        if ((item.userId ?: 0) <= 0) {
            return
        }

        borrows.add(item)
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

        borrows.replaceAll { if (it.id == item.id) item else it }
    }

    override fun delete(itemId: Int?) {
        if (itemId == null) {
            return
        }

        borrows.removeIf { it.id == itemId }
    }

    override fun getBorrowsFromUser(userId: Int?): List<Borrow> {
        if ((userId ?: 0) <= 0) {
            return emptyList()
        }

        return borrows.filter { it.userId == userId }
    }

    override fun getBorrowsFromBook(bookId: Int?): List<Borrow> {
        if ((bookId ?: 0) <= 0) {
            return emptyList()
        }

        return borrows.filter { it.bookId == bookId }
    }
}