package com.example.librarymanagementsystem.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.librarymanagementsystem.databinding.ItemAdapterBinding
import com.example.librarymanagementsystem.models.User

class ItemAdapter(
    private val items: MutableList<User>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = items[position]
        holder.binding.title.text = user.name
        holder.binding.description.text = user.surname
    }

    override fun getItemCount() = items.size
}
