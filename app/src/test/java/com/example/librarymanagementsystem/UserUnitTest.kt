package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.borrow.BorrowDaoMock
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import com.example.librarymanagementsystem.data.repository.user.UserDaoMock
import com.example.librarymanagementsystem.data.repository.user.UserRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.*

class UserUnitTest {

    private val repository by lazy { UserRepository(UserDaoMock(), borrowRepository) }
    private val borrowRepository by lazy { BorrowRepository(BorrowDaoMock()) }

    @Before
    fun setUp() = runTest {
        repository.truncate()
        borrowRepository.truncate()
    }

    @Test
    fun `test insert valid user`() = runTest {
        val user = getUserMock()
        repository.insert(user)

        val userList = repository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test insert user with empty name`() = runTest {
        val user = User(id = 1, name = "", surname = "Pereira")
        repository.insert(user)

        val userList = repository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test update valid user`() = runTest {
        val user = getUserMock()
        repository.insert(user)

        val updatedUser = User(id = 1, name = "Jeferson2", surname = "Pereira2")
        repository.update(updatedUser)

        val userList = repository.getList()
        assertEquals(1, userList.size)
        assertEquals("Jeferson2", userList[0].name)
        assertEquals("Pereira2", userList[0].surname)
    }

    @Test
    fun `test update user with empty name or surname`() = runTest {
        val user = getUserMock()
        repository.insert(user)

        val updatedUser = User(id = 1, name = "", surname = "")
        repository.update(updatedUser)

        val userList = repository.getList()
        assertEquals("Jeferson", userList[0].name)
        assertEquals("Pereira", userList[0].surname)
    }

    @Test
    fun `test delete existing user`() = runTest {
        val user = getUserMock()

        repository.insert(user)
        repository.delete(user.id)

        val userList = repository.getList()
        assertTrue(userList.isEmpty())
    }

    @Test
    fun `test delete user with null id`() = runTest {
        val user = getUserMock()
        repository.insert(user)

        val invalidUser = User(id = 2, name = "Invalid")
        repository.delete(invalidUser.id)

        val userList = repository.getList()
        assertEquals(1, userList.size)
    }

    @Test
    fun findUserWithId() = runTest {
        val user = getUserMock()
        repository.insert(user)

        val item = repository.find(1)
        assertEquals("Jeferson", item?.name)
        assertEquals("Pereira", item?.surname)
    }

    @Test
    fun invalidDeleteUserWithBorrow() = runTest {
        repository.truncate()

        repository.insert(getUserMock())

        val item = repository.find(1)
        assertNotNull(item)

        borrowRepository.truncate()
        borrowRepository.insert(Borrow(1, 1, 1))

        repository.delete(item?.id ?: User().id)

        val emptyList = repository.getList()
        assertEquals(1, emptyList.size)
    }

    private fun getUserMock() = User(id = 1, name = "Jeferson", surname = "Pereira")
}