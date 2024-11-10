package com.example.librarymanagementsystem.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.databinding.ActivityBorrowBinding

class BorrowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorrowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}