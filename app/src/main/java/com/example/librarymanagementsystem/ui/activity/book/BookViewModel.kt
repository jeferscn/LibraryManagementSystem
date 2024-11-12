package com.example.librarymanagementsystem.ui.activity.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.BookRepository

class BookViewModel : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun setup() {
        updateBookList()
    }

    fun save(book: Book) {
        if (book.id == null) {
            BookRepository.insert(book)
        } else {
            BookRepository.update(book)
        }

        updateBookList()
    }

    fun delete(book: Book) {
        BookRepository.delete(book)
        updateBookList()
    }

    private fun updateBookList() {
        _bookList.value = BookRepository.getList()
    }
}