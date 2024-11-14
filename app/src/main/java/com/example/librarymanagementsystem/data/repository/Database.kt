package com.example.librarymanagementsystem.data.repository

import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User

object Database {
    val books = mutableListOf<Book>()
    val users = mutableListOf<User>()
    val borrows = mutableListOf<Borrow>()
}