package com.example.librarymanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Book

class BookViewModel : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun addBook(title: String, description: String?, imageUrl: String?) {
        val currentList = _bookList.value.orEmpty().toMutableList()

        val book = Book(
            id = currentList.size.toString(),
            title = title,
            description = description,
            imageUrl = imageUrl
        )

        _bookList.value = currentList.also { it.add(book) }
    }
}