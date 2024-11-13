package com.example.librarymanagementsystem.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityHomeBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.manager.NavigationManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnBorrow.setSafeOnClickListener {
            NavigationManager.goToBorrow(this)
        }

        binding.btnBooks.setSafeOnClickListener {
            NavigationManager.goToBooks(this)
        }

        binding.btnUsers.setSafeOnClickListener {
            NavigationManager.goToUsers(this)
        }
    }
}