package com.example.librarymanagementsystem.manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.ui.activity.book.BookListActivity
import com.example.librarymanagementsystem.ui.activity.borrow.BorrowActivity
import com.example.librarymanagementsystem.ui.activity.user.UserActivity

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