package com.example.librarymanagementsystem.data.repository.user

import com.example.librarymanagementsystem.data.model.User

class UserDaoMock : UserDao {
    private val dataList = mutableListOf<User>()

    override fun getAll(): List<User> = dataList

    override fun insert(user: User) {
        dataList.add(user)
    }

    override fun update(user: User) {
        dataList.replaceAll { if (it.id == user.id) user else it }
    }

    override fun findById(userId: Int?): User? {
        return dataList.find { it.id == userId }
    }

    override fun deleteById(userId: Int?) {
        dataList.removeIf { it.id == userId }
    }

    override fun truncate() {
        dataList.clear()
    }
}