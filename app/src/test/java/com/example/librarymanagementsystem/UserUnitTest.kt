package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.book.BookRepository
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import com.example.librarymanagementsystem.data.repository.user.UserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runners.Parameterized.BeforeParam

class UserUnitTest {

    private val repository by lazy { UserRepository(borrowRepository) }
    private val borrowRepository by lazy { BorrowRepository() }

    @Before
    fun setUp() {
        repository.truncate()
        borrowRepository.truncate()
    }

    @Test
    fun `test insert valid user`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        repository.insert(user)

        val userList = repository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test insert user with empty name`() {
        val user = User(name = "", surname = "Pereira")
        repository.insert(user)

        val userList = repository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test update valid user`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        repository.insert(user)

        val updatedUser = User(id = 1, name = "Jeferson2", surname = "Pereira2")
        repository.update(updatedUser)

        val userList = repository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson2", userList[0].name)
        assertEquals("Pereira2", userList[0].surname)
    }

    @Test
    fun `test update user with empty name or surname`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        repository.insert(user)

        val updatedUser = User(id = 1, name = "", surname = "")
        repository.update(updatedUser)

        val userList = repository.getList()
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test delete existing user`() {
        val user = User(name = "Jeferson", surname = "Pereira")

        repository.insert(user)
        repository.delete(user)

        val userList = repository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test delete user with null id`() {
        val user = User(name = "Jeferson", surname = "Pereira")
        repository.insert(user)

        val invalidUser = User(name = "Invalid")
        repository.delete(invalidUser)

        val userList = repository.getList()
        assertEquals(1, userList.size)
    }

    @Test
    fun findUserWithId() {
        val user = User(name = "Jeferson", surname = "Pereira")
        repository.insert(user)

        val item = repository.find(1)
        assertEquals("Jeferson", item?.name)
        assertEquals("Pereira", item?.surname)
    }

    @Test
    fun invalidDeleteUserWithBorrow() {
        repository.truncate()

        repository.insert(User(name = "Jeferson", surname = "Pereira"))

        val item = repository.find(1)
        assertNotNull(item)

        borrowRepository.truncate()
        borrowRepository.insert(Borrow(1, 1, 1))

        repository.delete(item ?: User())

        val emptyList = repository.getList()
        assertEquals(1, emptyList.size)
    }
}