package com.example.librarymanagementsystem.ui.activity.book

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBookListBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.book.BookAdapter
import com.example.librarymanagementsystem.ui.book.BookItemModal

class BookListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookListBinding

    private val viewmodel by viewModels<BookViewModel>()

    private val adapter by lazy { BookAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookListBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewmodel.setup()

        setupInterface()
    }

    private fun setupInterface() {
        binding.listItems.adapter = adapter

        viewmodel.bookList.observe(this) {
            if (it.isEmpty()) {
                binding.txtBookListEmpty.visibility = View.VISIBLE
            } else {
                binding.txtBookListEmpty.visibility = View.GONE
            }

            adapter.submitList(it)
        }

        binding.btnAddAction.setSafeOnClickListener {
            BookItemModal.newInstance().show(supportFragmentManager, "BookItemModal")
        }
    }
}