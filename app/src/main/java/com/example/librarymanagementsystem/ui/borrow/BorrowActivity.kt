package com.example.librarymanagementsystem.ui.borrow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBorrowBinding

class BorrowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorrowBinding

    private val viewmodel by viewModels<BorrowViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBorrowBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}