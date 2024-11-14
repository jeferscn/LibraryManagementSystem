package com.example.librarymanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.book.BookInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookInterface
) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun setup() {
        updateBookList()
    }

    fun save(book: Book) {
        if (book.id == null) {
            bookRepository.insert(book)
        } else {
            bookRepository.update(book)
        }

        updateBookList()
    }

    fun delete(book: Book): Boolean = bookRepository.delete(book.id).also { success ->
        if (success) {
            updateBookList()
        }
    }

    fun getMockData(): Book = bookRepository.getMockData()

    private fun updateBookList() {
        _bookList.value = bookRepository.getList()
    }
}