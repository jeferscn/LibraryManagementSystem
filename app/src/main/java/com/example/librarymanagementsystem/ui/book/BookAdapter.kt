package com.example.librarymanagementsystem.ui.book

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book

class BookAdapter(
    private var bookList: List<Book>
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun getItemCount(): Int = bookList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<Book>) {
        bookList = list
        notifyDataSetChanged()
    }

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.textViewDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.imageViewBookCover)

        fun bind(book: Book) {
            titleTextView.text = book.title
            descriptionTextView.text = book.description

            if (book.imageUrl.isNullOrEmpty()) {
                imageView.setImageResource(R.drawable.ic_baseline_add_photo_alternate_24)
            } else {
                loadImage(book.imageUrl)
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
