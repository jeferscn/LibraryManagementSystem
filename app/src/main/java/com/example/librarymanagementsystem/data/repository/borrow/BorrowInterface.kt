package com.example.librarymanagementsystem.data.repository.borrow

import com.example.librarymanagementsystem.data.model.Borrow

interface BorrowInterface {
    fun truncate()
    fun getList(): List<Borrow>
    fun insert(item: Borrow)
    fun update(item: Borrow)
    fun delete(itemId: Int?)
    fun getBorrowsFromUser(userId: Int?): List<Borrow>
    fun getBorrowsFromBook(bookId: Int?): List<Borrow>
}