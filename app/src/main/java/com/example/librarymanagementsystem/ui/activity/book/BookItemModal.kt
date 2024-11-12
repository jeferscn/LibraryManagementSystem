package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.repository.BookRepository
import com.example.librarymanagementsystem.databinding.DialogBookItemBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.activity.book.BookViewModel
import com.example.librarymanagementsystem.ui.base.BaseModal

class BookItemModal : BaseModal() {

    private lateinit var binding: DialogBookItemBinding

    private val viewmodel by activityViewModels<BookViewModel>()

    private lateinit var bookItem: Book

    companion object {
        private const val BOOK_ID = "id"
        private const val BOOK_TITLE = "title"
        private const val BOOK_DESCRIPTION = "description"
        private const val BOOK_IMAGE_URL = "imageUrl"

        fun newInstance(book: Book? = null) = BookItemModal().apply {
            arguments = Bundle().apply {
                putInt(BOOK_ID, book?.id ?: 0)
                putString(BOOK_TITLE, book?.title)
                putString(BOOK_DESCRIPTION, book?.description)
                putString(BOOK_IMAGE_URL, book?.imageUrl)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogBookItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookItem = getBookItem()

        setupBookData()

        binding.buttonSubmit.setSafeOnClickListener {
            submitData()
            dismiss()
        }

        if (bookItem.id == null) {
            binding.buttonDelete.visibility = View.INVISIBLE
        } else {
            binding.buttonDelete.visibility = View.VISIBLE

            binding.buttonDelete.setSafeOnClickListener {
                viewmodel.delete(bookItem)
                dismiss()
            }
        }
    }

    private fun setupBookData() {
        binding.editTextTitle.setText(bookItem.title)
        binding.editTextDesc.setText(bookItem.description)
        binding.editTextImageUrl.setText(bookItem.imageUrl)
    }

    private fun submitData() {
        if (!hasValidData()) {
            Toast.makeText(requireContext(), getString(R.string.book_form_validation_error), Toast.LENGTH_SHORT).show()
            return
        }

        bookItem.title = binding.editTextTitle.text.toString()
        bookItem.description = binding.editTextDesc.text.toString().takeIf { it.isNotBlank() }
        bookItem.imageUrl = binding.editTextImageUrl.text.toString().takeIf { it.isNotBlank() }

        viewmodel.save(bookItem)

        dismiss()
    }

    private fun getBookItem(): Book  = if (isEditingItem()) {
        Book(
            id = arguments?.getInt(BOOK_ID),
            title = arguments?.getString(BOOK_TITLE) ?: "",
            description = arguments?.getString(BOOK_DESCRIPTION),
            imageUrl = arguments?.getString(BOOK_IMAGE_URL)
        )
    } else {
        BookRepository.getMockData()
    }

    private fun isEditingItem() = (arguments?.getInt(BOOK_ID) ?: 0) > 0

    private fun hasValidData() = binding.editTextTitle.text.toString().isNotBlank()
}