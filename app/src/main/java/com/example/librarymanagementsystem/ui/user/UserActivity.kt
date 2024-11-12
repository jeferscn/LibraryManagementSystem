package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.librarymanagementsystem.adapters.ItemAdapter
import com.example.librarymanagementsystem.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    private val viewmodel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd

        binding.recyclerList.apply {
            layoutManager = LinearLayoutManager(this@UserActivity)
            adapter = ItemAdapter(viewmodel.getItems())
        }
    }
}