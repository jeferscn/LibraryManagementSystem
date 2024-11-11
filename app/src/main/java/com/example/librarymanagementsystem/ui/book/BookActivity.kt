package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBooksBinding

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBooksBinding

    private val viewmodel by viewModels<BookViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}