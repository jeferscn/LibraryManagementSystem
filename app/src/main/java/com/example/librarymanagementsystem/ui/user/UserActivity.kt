package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.librarymanagementsystem.databinding.ActivityUserBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewmodel by viewModels<UserViewModel>()

    @Inject lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewmodel.updateUserList()

        setupInterface()
    }

    private fun setupInterface() {
        val items = viewmodel.users.value
        binding.listItems.adapter = adapter

        viewmodel.users.observe(this) { users ->
            setupEmptyListMessage(items.isNullOrEmpty())
            adapter.submitList(users)
        }

        binding.btnAddAction.setSafeOnClickListener {
            UserModal.newInstance().show(supportFragmentManager, UserModal::class.java.simpleName)
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