package com.example.librarymanagementsystem.ui.borrow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.borrow.BorrowRepository

class BorrowViewModel : ViewModel() {

    private val _borrowList = MutableLiveData<List<Borrow>>()
    val borrowList: LiveData<List<Borrow>> = _borrowList

    fun setup() {
        updateBorrowList()
    }

    fun save(book: Borrow) {
        if (book.id == null) {
            BorrowRepository.insert(book)
        } else {
            BorrowRepository.update(book)
        }

        updateBorrowList()
    }

    fun delete(book: Borrow) {
        BorrowRepository.delete(book.id)
        updateBorrowList()
    }

    private fun updateBorrowList() {
        _borrowList.value = BorrowRepository.getList()
    }
}