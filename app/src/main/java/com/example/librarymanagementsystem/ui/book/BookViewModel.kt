package com.example.librarymanagementsystem.ui.book

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagementsystem.data.di.IoDispatcher
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.book.BookInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val bookRepository: BookInterface
) : ViewModel() {

    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    fun setup() {
        viewModelScope.launch {
            updateBookList()
        }
    }

    fun save(book: Book) = viewModelScope.launch(ioDispatcher) {
        if (book.id == null) {
            bookRepository.insert(book)
        } else {
            bookRepository.update(book)
        }

        updateBookList()
    }

    fun delete(book: Book, callback: (Boolean) -> Unit) = viewModelScope.launch(ioDispatcher) {
        bookRepository.delete(book.id).also { success ->
            if (success) {
                updateBookList()
            }

            viewModelScope.launch {
                callback(success)
            }
        }
    }

    fun getMockData(): Book = runBlocking {
        bookRepository.getMockData()
    }

    private suspend fun updateBookList() = withContext(ioDispatcher) {
        _bookList.postValue(bookRepository.getList())
    }
}