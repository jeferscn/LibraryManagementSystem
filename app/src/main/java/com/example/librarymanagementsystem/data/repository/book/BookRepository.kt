package com.example.librarymanagementsystem.data.repository.book

import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository
import kotlin.random.Random

/**
 * Mock repository for books
 */
object BookRepository: BookInterface {
    private val mockTitles = listOf(
        "The Great Gatsby",
        "To Kill a Mockingbird",
        "1984",
        "Pride and Prejudice",
        "The Catcher in the Rye"
    )

    private val mockDescriptions = listOf(
        "A novel by F. Scott Fitzgerald",
        "A novel by Harper Lee",
        "A novel by George Orwell",
        "A novel by Jane Austen",
        "A novel by J.D. Salinger",
    )

    private val mockImages = listOf(
        "http://bookcoverarchive.com/wp-content/uploads/2020/07/91bh9jVbRZL.jpg",
        "http://bookcoverarchive.com/wp-content/uploads/2020/07/8ecceb85-a50f-4e5d-a739-bec11f2373c5.jpeg",
        "http://bookcoverarchive.com/wp-content/uploads/2015/06/bloodsplatters1st-uncorrected_txtwithcvf1.png",
        "http://bookcoverarchive.com/wp-content/uploads/amazon/wall_street.jpg",
        "http://bookcoverarchive.com/wp-content/uploads/2016/03/A1Pim60eMZL.jpg"
    )

    private val bookList = mutableListOf<Book>()

    override fun truncate() {
        bookList.clear()
    }

    override fun getList(): List<Book> = bookList

    override fun insert(book: Book) {
        book.id = bookList.size + 1

        if (book.title.isNullOrEmpty()) {
            return
        }

        bookList.add(book)
    }

    override fun update(book: Book) {
        if (book.id == null || book.title.isNullOrEmpty()) {
            return
        }

        bookList.replaceAll { if (it.id == book.id) book else it }
    }

    override fun delete(bookId: Int?): Boolean {
        if (bookId == null) {
            return false
        }

        val hasBorrow = BorrowRepository.getBorrowsFromBook(bookId).isNotEmpty()

        if (hasBorrow) {
            return false
        }

        bookList.removeIf { it.id == bookId }

        return true
    }

    override fun find(bookId: Int?): Book? {
        return bookList.find { it.id == bookId }
    }

    override fun getMockData(): Book {
        val mockPosition = Random.nextInt(0, mockTitles.size - 1)

        return Book(
            title = mockTitles[mockPosition],
            description = mockDescriptions[mockPosition],
            imageUrl = mockImages[mockPosition]
        )
    }
}