package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User

interface UserInterface {
    suspend fun truncate()
    suspend fun getList(): List<User>
    suspend fun insert(user: User)
    suspend fun update(user: User)
    suspend fun delete(userId: Int?): Boolean
    suspend fun find(userId: Int?): User?
}