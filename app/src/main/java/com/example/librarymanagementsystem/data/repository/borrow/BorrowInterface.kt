package com.example.librarymanagementsystem.data.repository.borrow

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.BorrowWithDetails

interface BorrowInterface {
    suspend fun truncate()
    suspend fun getList(): List<Borrow>
    suspend fun getListWithDetails(): List<BorrowWithDetails>
    suspend fun insert(item: Borrow)
    suspend fun update(item: Borrow)
    suspend fun delete(itemId: Int?)
    suspend fun find(itemId: Int?): Borrow?
    suspend fun hasBorrowsFromUser(userId: Int?): Boolean
    suspend fun hasBorrowsFromBook(bookId: Int?): Boolean
}