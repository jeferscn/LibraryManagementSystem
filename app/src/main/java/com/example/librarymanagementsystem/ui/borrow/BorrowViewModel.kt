package com.example.librarymanagementsystem.ui.borrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.borrow.BorrowInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BorrowViewModel @Inject constructor(
    private val borrowRepository: BorrowInterface?
): ViewModel() {

    private val _borrowList = MutableLiveData<List<Borrow>>()
    val borrowList: LiveData<List<Borrow>> = _borrowList

    fun setup() {
        updateBorrowList()
    }

    fun save(book: Borrow) {
        if (book.id == null) {
            borrowRepository?.insert(book)
        } else {
            borrowRepository?.update(book)
        }

        updateBorrowList()
    }

    fun delete(book: Borrow) {
        borrowRepository?.delete(book.id)
        updateBorrowList()
    }

    private fun updateBorrowList() {
        _borrowList.value = borrowRepository?.getList()
    }
}