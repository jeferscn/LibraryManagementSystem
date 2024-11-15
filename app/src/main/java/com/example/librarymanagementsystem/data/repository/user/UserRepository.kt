package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.borrow.BorrowInterface
import javax.inject.Inject

class UserRepository @Inject constructor(
    private var database: UserDao,
    private var borrowRepository: BorrowInterface
): UserInterface {
    override suspend fun truncate() {
        database.truncate()
    }

    override suspend fun getList(): List<User> = database.getAll()

    override suspend fun find(userId: Int?): User? = database.findById(userId)

    override suspend fun insert(user: User) {
        if (user.name.isNullOrEmpty()) {
            return
        }

        database.insert(user)
    }

    override suspend fun update(user: User) {
        if (user.name.isNullOrEmpty() || user.surname.isNullOrEmpty()) {
            return
        }

        database.update(user)
    }

    override suspend fun delete(userId: Int?): Boolean {
        if (userId == null) {
            return false
        }
        if (borrowRepository.hasBorrowsFromUser(userId)) {
            return false
        }

        database.deleteById(userId)

        return true
    }
}