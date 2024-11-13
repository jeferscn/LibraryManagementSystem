package com.example.librarymanagementsystem.ui.borrow

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityBorrowBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BorrowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBorrowBinding

    private val viewmodel by viewModels<BorrowViewModel>()

    @Inject lateinit var adapter: BorrowAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBorrowBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewmodel.setup()

        setupInterface()
    }

    private fun setupInterface() {
        binding.listItems.adapter = adapter

        viewmodel.borrowList.observe(this) {
            setupEmptyListMessage(it.isEmpty())
            adapter.submitList(it)
        }

        binding.btnAddAction.setSafeOnClickListener {
            BorrowModal.newInstance().show(supportFragmentManager, BorrowModal::class.java.simpleName)
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