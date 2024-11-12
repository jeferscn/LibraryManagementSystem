package com.example.librarymanagementsystem.manager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.ui.borrow.BorrowActivity
import com.example.librarymanagementsystem.ui.user.UserActivity
import com.example.librarymanagementsystem.ui.book.BookActivity

object NavigationManager {

    fun goToBorrow(context: AppCompatActivity) {
        val intent = Intent(context, BorrowActivity::class.java)
        context.startActivity(intent)
    }

    fun goToBooks(context: AppCompatActivity) {
        val intent = Intent(context, BookActivity::class.java)
        context.startActivity(intent)
    }

    fun goToUsers(context: AppCompatActivity) {
        val intent = Intent(context, UserActivity::class.java)
        context.startActivity(intent)
    }
}