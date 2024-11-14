package com.example.librarymanagementsystem.ui.borrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagementsystem.data.di.IoDispatcher
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.BorrowWithDetails
import com.example.librarymanagementsystem.data.repository.borrow.BorrowInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BorrowViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val borrowRepository: BorrowInterface?
): ViewModel() {

    private val _borrowList = MutableLiveData<List<BorrowWithDetails>>()
    val borrowList: LiveData<List<BorrowWithDetails>> = _borrowList

    fun setup() {
        viewModelScope.launch {
            updateBorrowList()
        }
    }

    fun save(book: Borrow) = viewModelScope.launch(ioDispatcher) {
        if (book.id == null) {
            borrowRepository?.insert(book)
        } else {
            borrowRepository?.update(book)
        }

        updateBorrowList()
    }

    fun delete(book: Borrow) = viewModelScope.launch(ioDispatcher) {
        borrowRepository?.delete(book.id)
        updateBorrowList()
    }

    private suspend fun updateBorrowList() = withContext(ioDispatcher) {
        _borrowList.postValue(borrowRepository?.getListWithDetails())
    }
}