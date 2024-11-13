package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.book.BookRepository
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import org.junit.Test

class BookUnitTest {

    private val repository by lazy { BookRepository }

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

    @Test
    fun getListWithValues() {
        repository.truncate()

        val items = repository.getList().toMutableList()

        items.add(getBookMock())

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

        repository.insert(getBookMock())

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
    fun addItemWithRequiredEmptyValuesIntoList() {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getBookMock(null, "", "", ""))

        val list = repository.getList()
        assertEquals(0, list.size)

        val item = repository.getList().firstOrNull()
        assertNull(item)
    }

    @Test
    fun addItemWithRequiredTitleOnlyIntoList() {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getBookMock(null, "Title", "", ""))

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
    fun removeItemAfterAddingIntoList() {
        repository.truncate()

        val initialList = repository.getList()
        assertEquals(0, initialList.size)

        repository.insert(getBookMock(null))

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

        repository.insert(getBookMock(null, "", "", ""))

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
    fun findBookWithId() {
        repository.truncate()

        repository.insert(getBookMock())

        val item = repository.find(1)
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals("Title", item?.title)
        assertEquals("Description", item?.description)
        assertEquals("Image", item?.imageUrl)
    }

    @Test
    fun invalidDeleteBookBorrowed() {
        repository.truncate()

        repository.insert(getBookMock())

        val item = repository.find(1)
        assertNotNull(item)

        BorrowRepository.insert(Borrow(1, 1, 1))

        repository.delete(item?.id)

        val emptyList = repository.getList()
        assertEquals(1, emptyList.size)
    }
}