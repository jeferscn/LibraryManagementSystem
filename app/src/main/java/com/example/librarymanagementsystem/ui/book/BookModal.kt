package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.databinding.ModalBookBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.base.BaseModal

class BookModal : BaseModal() {

    private lateinit var binding: ModalBookBinding

    private val viewmodel by activityViewModels<BookViewModel>()

    private lateinit var bookItem: Book

    companion object {
        private const val BOOK_ID = "id"
        private const val BOOK_TITLE = "title"
        private const val BOOK_DESCRIPTION = "description"
        private const val BOOK_IMAGE_URL = "imageUrl"

        fun newInstance(book: Book? = null) = BookModal().apply {
            arguments = Bundle().apply {
                putInt(BOOK_ID, book?.id ?: 0)
                putString(BOOK_TITLE, book?.title)
                putString(BOOK_DESCRIPTION, book?.description)
                putString(BOOK_IMAGE_URL, book?.imageUrl)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookItem = getBookItem()

        setupBookData()

        binding.btnSave.setSafeOnClickListener {
            submitData()
            dismiss()
        }

        if (bookItem.id == null) {
            binding.btnDelete.visibility = View.INVISIBLE
        } else {
            binding.btnDelete.visibility = View.VISIBLE

            binding.btnDelete.setSafeOnClickListener {
                viewmodel.delete(bookItem) { success ->
                    if (!success) {
                        displayError(getString(R.string.book_delete_error))
                    }

                    dismiss()
                }
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
            return displayError(getString(R.string.book_form_validation_error))
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
        viewmodel.getMockData()
    }

    private fun displayError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun isEditingItem() = (arguments?.getInt(BOOK_ID) ?: 0) > 0

    private fun hasValidData() = binding.editTextTitle.text.toString().isNotBlank()
}