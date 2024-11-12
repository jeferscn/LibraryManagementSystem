package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityUserBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewmodel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddAction.setSafeOnClickListener {
            UserModal().show(supportFragmentManager, UserModal::class.java.simpleName)
        }

        viewmodel.users.observe(this) {
            binding.listItems.apply {
                val items = viewmodel.getItems()

                setupEmptyListMessage(items.isEmpty())

                adapter = UserAdapter(items)
            }
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