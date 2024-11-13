package com.example.librarymanagementsystem.data.repository

import com.example.librarymanagementsystem.data.model.User

object UserRepository {

    private val userList = mutableListOf<User>()

    fun truncate() {
        userList.clear()
    }

    fun getList(): List<User> = userList

    fun insert(user: User) {
        user.id = userList.size + 1

        if (user.name.isNullOrEmpty()) {
            return
        }

        userList.add(user)
    }

    fun update(user: User) {
        if (user.name.isNullOrEmpty() || user.surname.isNullOrEmpty()) {
            return
        }

        userList.replaceAll { if (it.id == user.id) user else it }
    }

    fun delete(user: User) {
        if (user.id == null) {
            return
        }

        val hasBorrow = BorrowRepository.getBorrowsFromUser(user.id).isNotEmpty()

        if (hasBorrow) {
            return
        }

        userList.removeIf { it.id == user.id }
    }

    fun find(userId: Int?): User? {
        return userList.find { it.id == userId }
    }
}