package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import com.example.librarymanagementsystem.data.repository.user.UserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class UserUnitTest {

    @Before
    fun setUp() {
        UserRepository.truncate()
    }

    @Test
    fun `test insert valid user`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)

        val userList = UserRepository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test insert user with empty name`() {
        val user = User(name = "", surname = "Pereira")
        UserRepository.insert(user)

        val userList = UserRepository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test update valid user`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)

        val updatedUser = User(id = 1, name = "Jeferson2", surname = "Pereira2")
        UserRepository.update(updatedUser)

        val userList = UserRepository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson2", userList[0].name)
        assertEquals("Pereira2", userList[0].surname)
    }

    @Test
    fun `test update user with empty name or surname`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)

        val updatedUser = User(id = 1, name = "", surname = "")
        UserRepository.update(updatedUser)

        val userList = UserRepository.getList()
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test delete existing user`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)
        UserRepository.delete(user)

        val userList = UserRepository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test delete user with null id`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)

        val invalidUser = User(name = "Invalid")
        UserRepository.delete(invalidUser)

        val userList = UserRepository.getList()
        assertEquals(1, userList.size)
    }

    @Test
    fun findUserWithId() {
        val user = User(name = "Jeferson", surname = "Pereira")
        UserRepository.insert(user)

        val item = UserRepository.find(1)
        assertEquals("Jeferson", item?.name)
        assertEquals("Pereira", item?.surname)
    }

    @Test
    fun invalidDeleteUserWithBorrow() {
        UserRepository.truncate()

        UserRepository.insert(User(name = "Jeferson", surname = "Pereira"))

        val item = UserRepository.find(1)
        assertNotNull(item)

        BorrowRepository.insert(Borrow(1, 1, 1))

        UserRepository.delete(item ?: User())

        val emptyList = UserRepository.getList()
        assertEquals(1, emptyList.size)
    }
}