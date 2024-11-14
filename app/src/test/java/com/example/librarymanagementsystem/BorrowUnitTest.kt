package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.*

class BorrowUnitTest {

    private val repository by lazy { BorrowRepository() }

    private fun getMockBorrow(
        id: Int? = null,
        bookId: Int? = null,
        userId: Int? = null
    ) = Borrow(
        id = id ?: 1,
        bookId = bookId ?: 1,
        userId = userId ?: 1
    )

    @Before
    fun setUp() {
        repository.truncate()
    }

    @Test
    fun getListWithValues() {
        repository.truncate()

        val items = repository.getList().toMutableList()

        items.add(getMockBorrow())

        assertEquals(true, items.isNotEmpty())
    }

    @Test
    fun getListWithoutValues() {
        repository.truncate()
        assertEquals(true, repository.getList().isEmpty())
    }

    @Test
    fun addItemIntoList() {
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
    fun addItemWithRequiredEmptyValuesIntoList() {
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
    fun removeItemAfterAddingIntoList() {
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
    fun removeItemAfterAddingWithFailureIntoList() {
        repository.truncate()

        repository.insert(getMockBorrow(null, 0, 0))

        val list = repository.getList()
        assertEquals(0, list.size)

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithEmptyList() {
        repository.truncate()

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithInvalidPosition() {
        repository.truncate()

        repository.delete(-1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun getBorrowsFromUser() {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val borrows = repository.getBorrowsFromUser(1)
        assertEquals(1, borrows.size)
    }

    @Test
    fun getBorrowsFromUserWithInvalidUserId() {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val borrows = repository.getBorrowsFromUser(0)
        assertEquals(0, borrows.size)
    }

    @Test
    fun getBorrowsFromUserWithEmptyList() {
        repository.truncate()

        val borrows = repository.getBorrowsFromUser(1)
        assertEquals(0, borrows.size)
    }

    @Test
    fun getBorrowsFromBook() {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val borrows = repository.getBorrowsFromBook(1)
        assertEquals(1, borrows.size)
    }

    @Test
    fun getBorrowsFromBookWithInvalidBookId() {
        repository.truncate()

        repository.insert(getMockBorrow(1, 1, 1))

        val borrows = repository.getBorrowsFromBook(0)
        assertEquals(0, borrows.size)
    }

    @Test
    fun getBorrowsFromBookWithEmptyList() {
        repository.truncate()

        val borrows = repository.getBorrowsFromBook(1)
        assertEquals(0, borrows.size)
    }
}