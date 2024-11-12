package com.example.librarymanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.BookRepository

class BookViewModel : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun addBook(title: String, description: String?, imageUrl: String?) {
        val book = Book(
            title = title,
            description = description,
            imageUrl = imageUrl
        )

        BookRepository.insertBook(book)

        _bookList.value = BookRepository.getBookList()
    }
}