package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBookListBinding
import com.example.librarymanagementsystem.extensions.setSafeOnClickListener

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding

    private val viewmodel by viewModels<BookViewModel>()

    private val adapter by lazy { BookAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupInterface()
    }

    private fun setupInterface() {
        binding.listItems.adapter = adapter

        viewmodel.bookList.observe(this) {
            adapter.submitList(it)
        }

        binding.btnAddAction.setSafeOnClickListener {
            BookItemModal().show(supportFragmentManager, "BookItemModal")
        }
    }
}