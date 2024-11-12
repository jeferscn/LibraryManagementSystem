package com.example.librarymanagementsystem.data.repository

import com.example.librarymanagementsystem.data.model.Book
import kotlin.random.Random

/**
 * Mock repository for books
 */
object BookRepository {
    private val mockTitles = listOf(
        "The Great Gatsby",
        "To Kill a Mockingbird",
        "1984",
        "Pride and Prejudice",
        "The Catcher in the Rye",
        "The Hobbit",
        "Fahrenheit 451"
    )

    private val mockDescriptions = listOf(
        "A novel by F. Scott Fitzgerald",
        "A novel by Harper Lee",
        "A novel by George Orwell",
        "A novel by Jane Austen",
        "A novel by J.D. Salinger",
    )

    private val mockImages = listOf(
        "https://f.i.uol.com.br/fotografia/2023/04/13/16813903606437fb18c8902_1681390360_1x1_md.jpg",
        "https://www.istockphoto.com/resources/images/PhotoFTLP/1024x1024_1.jpg",
        "https://static.wixstatic.com/media/31a549_7dffb191bffa440686e5a148b8e042d9~mv2.jpg/v1/fill/w_480,h_768,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/31a549_7dffb191bffa440686e5a148b8e042d9~mv2.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTzxDrhq9H-FuI4XqxqND9mKRPWUu-NCe7hug&s",
        "https://marketplace.canva.com/EAD0UPCkitY/1/0/1024w/canva-capa-de-livro-de-suspense-monocrom%C3%A1tica-com-foto-de-floresta-U1dpnJ3bwKw.jpg",
        "https://i.pinimg.com/236x/14/9a/90/149a90aad3b735dbc24525ea79eff309.jpg",
    )

    private val bookList = mutableListOf<Book>()

    fun getBookList(): List<Book> = bookList

    fun insertBook(book: Book) {
        book.id = (bookList.size + 1).toString()
        bookList.add(book)
    }

    fun getMockData(): Book {
        val mockPosition = Random.nextInt(0, mockTitles.size - 1)

        return Book(
            title = mockTitles[mockPosition],
            description = mockDescriptions[mockPosition],
            imageUrl = mockImages[mockPosition]
        )
    }
}