package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBookBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener

class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding

    private val viewmodel by viewModels<BookViewModel>()

    private val adapter by lazy { BookAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewmodel.setup()

        setupInterface()
    }

    private fun setupInterface() {
        binding.listItems.adapter = adapter

        viewmodel.bookList.observe(this) {
            setupEmptyListMessage(it.isEmpty())
            adapter.submitList(it)
        }

        binding.btnAddAction.setSafeOnClickListener {
            BookModal.newInstance().show(supportFragmentManager, BookModal::class.java.simpleName)
        }
    }

    private fun setupEmptyListMessage(isEmptyList: Boolean) {
        binding.txtEmptyList.visibility = if (isEmptyList) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}