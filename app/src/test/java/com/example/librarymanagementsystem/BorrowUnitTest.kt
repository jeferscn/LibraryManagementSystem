package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.borrow.BorrowDaoMock
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.*

class BorrowUnitTest {

    private val repository by lazy { BorrowRepository(BorrowDaoMock()) }

    @Before
    fun setUp() = runTest {
        repository.truncate()
    }

    @Test
    fun getListWithValues() = runTest {
        repository.truncate()

        val items = repository.getList().toMutableList()

        items.add(getMockBorrow())

        assertEquals(true, items.isNotEmpty())
    }

    @Test
    fun getListWithoutValues() = runTest {
        repository.truncate()
        assertEquals(true, repository.getList().isEmpty())
    }

    @Test
    fun addItemIntoList() = runTest {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getMockBorrow())

        val list = repository.getList()
        assertEquals(1, list.size)

        val item = repository.getList().firstOrNull()
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals(1, item?.bookId)
        assertEquals(1, item?.userId)
    }

    @Test
    fun addItemWithRequiredEmptyValuesIntoList() = runTest {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getMockBorrow(0, 0, 0))

        val list = repository.getList()
        assertEquals(0, list.size)

        val item = repository.getList().firstOrNull()
        assertNull(item)
    }

    @Test
    fun removeItemAfterAddingIntoList() = runTest {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getMockBorrow(null))

        val list = repository.getList()
        assertEquals(1, list.size)

        val item = repository.getList().firstOrNull()
        assertNotNull(item)
        assertEquals(1, item?.id)

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemAfterAddingWithFailureIntoList() = runTest {
        repository.truncate()

        repository.insert(getMockBorrow(null, 0, 0))

        val list = repository.getList()
        assertEquals(0, list.size)

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithEmptyList() = runTest {
        repository.truncate()

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithInvalidPosition() = runTest {
        repository.truncate()

        repository.delete(-1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun getBorrowsFromUser() = runTest {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val hasBorrows = repository.hasBorrowsFromUser(1)
        assertEquals(true, hasBorrows)
    }

    @Test
    fun getBorrowsFromUserWithInvalidUserId() = runTest {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val hasBorrows = repository.hasBorrowsFromUser(0)
        assertEquals(false, hasBorrows)
    }

    @Test
    fun getBorrowsFromUserWithEmptyList() = runTest {
        repository.truncate()

        val hasBorrows = repository.hasBorrowsFromUser(1)
        assertEquals(false, hasBorrows)
    }

    @Test
    fun getBorrowsFromBook() = runTest {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val hasBorrows = repository.hasBorrowsFromBook(1)
        assertEquals(true, hasBorrows)
    }

    @Test
    fun getBorrowsFromBookWithInvalidBookId() = runTest {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val hasBorrows = repository.hasBorrowsFromBook(0)
        assertEquals(false, hasBorrows)
    }

    @Test
    fun getBorrowsFromBookWithEmptyList() = runTest {
        repository.truncate()

        val hasBorrows = repository.hasBorrowsFromBook(1)
        assertEquals(false, hasBorrows)
    }

    private fun getMockBorrow(
        id: Int? = null,
        bookId: Int? = null,
        userId: Int? = null
    ) = Borrow(
        id = id ?: 1,
        bookId = bookId ?: 1,
        userId = userId ?: 1
    )
}