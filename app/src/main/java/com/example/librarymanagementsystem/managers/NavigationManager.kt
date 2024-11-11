package com.example.librarymanagementsystem.managers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.ui.BooksActivity
import com.example.librarymanagementsystem.ui.BorrowActivity
import com.example.librarymanagementsystem.ui.UsersActivity

object NavigationManager {

    fun goToBorrow(context: AppCompatActivity) {
        val intent = Intent(context, BorrowActivity::class.java)
        context.startActivity(intent)
    }

    fun goToBooks(context: AppCompatActivity) {
        val intent = Intent(context, BooksActivity::class.java)
        context.startActivity(intent)
    }

    fun goToUsers(context: AppCompatActivity) {
        val intent = Intent(context, UsersActivity::class.java)
        context.startActivity(intent)
    }
}