package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User

interface UserInterface {
    fun truncate()
    fun getList(): List<User>
    fun insert(user: User)
    fun update(user: User)
    fun delete(user: User): Boolean
    fun find(userId: Int?): User?
}