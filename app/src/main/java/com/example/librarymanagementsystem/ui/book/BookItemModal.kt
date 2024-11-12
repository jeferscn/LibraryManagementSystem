package com.example.librarymanagementsystem.ui.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.databinding.DialogBookItemBinding
import com.example.librarymanagementsystem.extensions.setSafeOnClickListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BookItemModal : BottomSheetDialogFragment() {

    private lateinit var binding: DialogBookItemBinding

    private val viewmodel by activityViewModels<BookViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DialogBookItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSubmit.setSafeOnClickListener {
            submitData()
            dismiss()
        }
    }

    private fun submitData() {
        if (!hasValidData()) {
            Toast.makeText(requireContext(), getString(R.string.book_form_validation_error), Toast.LENGTH_SHORT).show()
            return
        }

        viewmodel.addBook(
            title = binding.editTextTitle.text.toString(),
            description = binding.editTextDesc.text.toString().takeIf { it.isNotBlank() },
            imageUrl = binding.editTextImageUrl.text.toString().takeIf { it.isNotBlank() }
        )

        dismiss()
    }

    private fun hasValidData() = binding.editTextTitle.text.toString().isNotBlank()
}