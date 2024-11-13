package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository

object UserRepository: UserInterface {

    private val userList = mutableListOf<User>()

    override fun truncate() {
        userList.clear()
    }

    override fun getList(): List<User> = userList

    override fun insert(user: User) {
        user.id = userList.size + 1

        if (user.name.isNullOrEmpty()) {
            return
        }

        userList.add(user)
    }

    override fun update(user: User) {
        if (user.name.isNullOrEmpty() || user.surname.isNullOrEmpty()) {
            return
        }

        userList.replaceAll { if (it.id == user.id) user else it }
    }

    override fun delete(user: User): Boolean {
        if (user.id == null) {
            return false
        }

        val hasBorrow = BorrowRepository.getBorrowsFromUser(user.id).isNotEmpty()

        if (hasBorrow) {
            return false
        }

        userList.removeIf { it.id == user.id }

        return true
    }

    override fun find(userId: Int?): User? {
        return userList.find { it.id == userId }
    }
}