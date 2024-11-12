package com.example.librarymanagementsystem.managers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.ui.book.BookListActivity
import com.example.librarymanagementsystem.ui.borrow.BorrowActivity
import com.example.librarymanagementsystem.ui.user.UserActivity

object NavigationManager {

    fun goToBorrow(context: AppCompatActivity) {
        val intent = Intent(context, BorrowActivity::class.java)
        context.startActivity(intent)
    }

    fun goToBooks(context: AppCompatActivity) {
        val intent = Intent(context, BookListActivity::class.java)
        context.startActivity(intent)
    }

    fun goToUsers(context: AppCompatActivity) {
        val intent = Intent(context, UserActivity::class.java)
        context.startActivity(intent)
    }
}