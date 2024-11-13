package com.example.librarymanagementsystem.ui.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.databinding.AdapterUserBinding
import com.example.librarymanagementsystem.data.model.User

class UserAdapter(
    private var userList: List<User>?
) : RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = AdapterUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = userList?.get(position)
        holder.binding.name.text = user?.name
        holder.binding.surname.text = user?.surname
    }

    override fun getItemCount() = userList?.size ?: 0

    inner class ItemViewHolder(val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<User>) {
        userList = list
        notifyDataSetChanged()
    }
}
