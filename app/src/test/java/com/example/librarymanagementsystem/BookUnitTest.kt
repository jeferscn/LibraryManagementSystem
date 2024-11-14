package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.book.BookDaoMock
import com.example.librarymanagementsystem.data.repository.book.BookRepository
import com.example.librarymanagementsystem.data.repository.borrow.BorrowDaoMock
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.test.runTest
import org.junit.*

class BookUnitTest {

    private val repository by lazy { BookRepository(BookDaoMock(), borrowRepository) }
    private val borrowRepository by lazy { BorrowRepository(BorrowDaoMock()) }

    @Before
    fun setUp() = runTest {
        repository.truncate()
        borrowRepository.truncate()
    }

    @Test
    fun getListWithoutValues() = runTest {
        assertEquals(true, repository.getList().isEmpty())
    }

    @Test
    fun addItemIntoList() = runTest {
        val book = getBookMock()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(book)

        val list = repository.getList()
        assertEquals(1, list.size)

        val item = repository.getList().firstOrNull()
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals("Title", item?.title)
        assertEquals("Description", item?.description)
        assertEquals("Image", item?.imageUrl)
    }

    @Test
    fun addItemWithRequiredEmptyValuesIntoList() = runTest {
        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getBookMock(null, "", "", ""))

        val list = repository.getList()
        assertEquals(0, list.size)

        val item = repository.getList().firstOrNull()
        assertNull(item)
    }

    @Test
    fun addItemWithRequiredTitleOnlyIntoList() = runTest {
        val book = getBookMock(null, "Title", "", "")

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(book)

        val list = repository.getList()
        assertEquals(1, list.size)

        val item = repository.getList().firstOrNull()
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals("Title", item?.title)
        assertEquals("", item?.description)
        assertEquals("", item?.imageUrl)
    }

    @Test
    fun removeItemAfterAddingIntoList() = runTest {
        val book = getBookMock(null)

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(book)

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
        repository.insert(getBookMock(null, "", "", ""))

        val list = repository.getList()
        assertEquals(0, list.size)

        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithEmptyList() = runTest {
        repository.delete(1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun removeItemWithInvalidPosition() = runTest {
        repository.delete(-1)

        val emptyList = repository.getList()
        assertEquals(0, emptyList.size)
    }

    @Test
    fun findBookWithId() = runTest {
        val book = getBookMock()

        repository.insert(book)

        val item = repository.find(1)
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals("Title", item?.title)
        assertEquals("Description", item?.description)
        assertEquals("Image", item?.imageUrl)
    }

    @Test
    fun invalidDeleteBookBorrowed() = runTest {
        val book = getBookMock()

        repository.insert(book)

        val item = repository.find(1)
        assertNotNull(item)

        borrowRepository.insert(Borrow(1, 1, 1))

        repository.delete(item?.id)

        val emptyList = repository.getList()
        assertEquals(1, emptyList.size)
    }

    private fun getBookMock(
        id: Int? = null,
        title: String? = null,
        description: String? = null,
        imageUrl: String? = null
    ) = Book(
        id = id ?: 1,
        title = title ?: "Title",
        description = description ?: "Description",
        imageUrl = imageUrl ?: "Image"
    )
}