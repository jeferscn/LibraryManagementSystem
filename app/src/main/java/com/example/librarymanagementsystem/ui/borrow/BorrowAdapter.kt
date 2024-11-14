package com.example.librarymanagementsystem.ui.borrow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.BorrowWithDetails
import javax.inject.Inject

class BorrowAdapter @Inject constructor(
    private var bookList: List<BorrowWithDetails>
) : RecyclerView.Adapter<BorrowAdapter.BookViewHolder>() {

    override fun getItemCount(): Int = bookList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_borrow, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<BorrowWithDetails>) {
        bookList = list
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTextView: TextView = itemView.findViewById(R.id.textViewUsername)
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewBookTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewBookDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewBookCover)

        fun bind(borrowWithDetails: BorrowWithDetails) {
            val borrow = borrowWithDetails.borrow
            val book = borrowWithDetails.book
            val user = borrowWithDetails.user

            if (book == null || user == null) {
                return
            }

            usernameTextView.text = user.name
            titleTextView.text = book.title
            descriptionTextView.text = book.description

            if (book.imageUrl.isNullOrEmpty()) {
                imageView.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24)
            } else {
                loadImage(book.imageUrl)
            }

            itemView.setOnClickListener {
                (itemView.context as? AppCompatActivity)?.apply {
                    BorrowModal.newInstance(borrow).show(supportFragmentManager, BorrowModal::class.java.simpleName)
                }
            }
        }

        private fun loadImage(imageUrl: String?) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_baseline_add_photo_alternate_24)
                .into(imageView)
        }
    }
}