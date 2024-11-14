package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import com.example.librarymanagementsystem.data.repository.Database.users
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(): UserInterface {

    override fun truncate() {
        users.clear()
    }

    override fun getList(): List<User> = users

    override fun insert(user: User) {
        user.id = users.size + 1

        if (user.name.isNullOrEmpty()) {
            return
        }

        users.add(user)
    }

    override fun update(user: User) {
        if (user.name.isNullOrEmpty() || user.surname.isNullOrEmpty()) {
            return
        }

        users.replaceAll { if (it.id == user.id) user else it }
    }

    override fun delete(user: User): Boolean {
        if (user.id == null) {
            return false
        }

        val hasBorrow = BorrowRepository.getBorrowsFromUser(user.id).isNotEmpty()

        if (hasBorrow) {
            return false
        }

        users.removeIf { it.id == user.id }

        return true
    }

    override fun find(userId: Int?): User? {
        return users.find { it.id == userId }
    }
}