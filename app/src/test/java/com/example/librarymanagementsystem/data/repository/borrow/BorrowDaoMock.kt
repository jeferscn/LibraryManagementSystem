package com.example.librarymanagementsystem.data.repository.borrow

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.BorrowWithDetails

class BorrowDaoMock : BorrowDao {
    private val dataList = mutableListOf<Borrow>()

    override fun getAll(): List<Borrow> = dataList

    override fun insert(borrow: Borrow) {
        dataList.add(borrow)
    }

    override fun update(borrow: Borrow) {
        dataList.replaceAll { if (it.id == borrow.id) borrow else it }
    }

    override fun findById(borrowId: Int?): Borrow? {
        return dataList.find { it.id == borrowId }
    }

    override fun deleteById(borrowId: Int?) {
        dataList.removeIf { it.id == borrowId }
    }

    override fun truncate() {
        dataList.clear()
    }

    override fun getBorrowsFromBook(bookId: Int?): List<Borrow> {
        return dataList.filter { it.bookId == bookId }
    }

    override fun getBorrowsFromUser(userId: Int?): List<Borrow> {
        return dataList.filter { it.userId == userId }
    }

    override fun getAllBorrowsWithDetails(): List<BorrowWithDetails> {
        return dataList.map { BorrowWithDetails(it, null, null) }
    }
}