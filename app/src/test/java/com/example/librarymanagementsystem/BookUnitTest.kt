package com.example.librarymanagementsystem

import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.BookRepository
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
        title = title ?: "TitleTest",
        description = description ?: "DescriptionTest",
        imageUrl = imageUrl ?: "ImageUrlTest"
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
        assertEquals("TitleTest", item?.title)
        assertEquals("DescriptionTest", item?.description)
        assertEquals("ImageUrlTest", item?.imageUrl)
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

        repository.insert(getBookMock(null, "TitleTest", "", ""))

        val list = repository.getList()
        assertEquals(1, list.size)

        val item = repository.getList().firstOrNull()
        assertNotNull(item)

        assertEquals(1, item?.id)
        assertEquals("TitleTest", item?.title)
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
}