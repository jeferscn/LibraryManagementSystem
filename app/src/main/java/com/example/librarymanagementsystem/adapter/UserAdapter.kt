package com.example.librarymanagementsystem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.databinding.UserAdapterBinding
import com.example.librarymanagementsystem.model.User

class UserAdapter(
    private val items: MutableList<User>
) : RecyclerView.Adapter<UserAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: UserAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = UserAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = items[position]
        holder.binding.name.text = user.name
        holder.binding.surname.text = user.surname
    }

    override fun getItemCount() = items.size
}
