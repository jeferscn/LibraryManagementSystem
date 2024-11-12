package com.example.librarymanagementsystem.ui.activity.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.adapter.UserAdapter
import com.example.librarymanagementsystem.databinding.ActivityUserBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.dialog.UserDialogFragment

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewmodel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setSafeOnClickListener {
            UserDialogFragment().show(supportFragmentManager, UserDialogFragment::class.java.simpleName)
        }

        viewmodel.users.observe(this) {
            binding.recyclerList.apply {
                layoutManager = LinearLayoutManager(this@UserActivity)
                adapter = UserAdapter(viewmodel.getItems())
            }
        }
    }
}