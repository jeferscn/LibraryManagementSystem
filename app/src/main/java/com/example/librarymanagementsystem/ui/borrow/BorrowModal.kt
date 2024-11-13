package com.example.librarymanagementsystem.ui.borrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.repository.BookRepository
import com.example.librarymanagementsystem.data.repository.UserRepository
import com.example.librarymanagementsystem.databinding.ModalBorrowBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.base.BaseModal

class BorrowModal : BaseModal() {

    private lateinit var binding: ModalBorrowBinding

    private val viewmodel by activityViewModels<BorrowViewModel>()

    private lateinit var borrowItem: Borrow

    companion object {
        private const val BORROW_ID = "id"
        private const val BOOK_ID = "title"
        private const val USER_ID = "description"

        fun newInstance(item: Borrow? = null) = BorrowModal().apply {
            arguments = Bundle().apply {
                putInt(BORROW_ID, item?.id ?: 0)
                putInt(BOOK_ID, item?.bookId ?: 0)
                putInt(USER_ID, item?.userId ?: 0)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalBorrowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        borrowItem = getBorrowItem()

        setupBookData()

        binding.btnSave.setSafeOnClickListener {
            submitData()
            dismiss()
        }

        if (borrowItem.id == null) {
            binding.btnDelete.visibility = View.INVISIBLE
        } else {
            binding.btnDelete.visibility = View.VISIBLE

            binding.btnDelete.setSafeOnClickListener {
                viewmodel.delete(borrowItem)
                dismiss()
            }
        }
    }

    private fun setupBookData() {
        val book = BookRepository.find(borrowItem.bookId)
        val user = UserRepository.find(borrowItem.userId)

        if (book == null || user == null) {
            return
        }

        binding.editTextUserId.setText(user.name)
        binding.editTextBookId.setText(book.title)
    }

    private fun submitData() {
        if (!hasValidData()) {
            Toast.makeText(requireContext(), getString(R.string.book_form_validation_error), Toast.LENGTH_SHORT).show()
            return
        }

        runCatching {
            borrowItem.userId = binding.editTextUserId.text.toString().toInt()
        }
        runCatching {
            borrowItem.bookId = binding.editTextBookId.text.toString().toInt()
        }

        viewmodel.save(borrowItem)

        dismiss()
    }

    private fun getBorrowItem(): Borrow  = if (isEditingItem()) {
        Borrow(
            id = arguments?.getInt(BORROW_ID),
            bookId = arguments?.getInt(BOOK_ID),
            userId = arguments?.getInt(USER_ID)
        )
    } else {
        Borrow()
    }

    private fun isEditingItem() = (arguments?.getInt(BORROW_ID) ?: 0) > 0

    private fun hasValidData(): Boolean {
        val hasUserSelected = binding.editTextUserId.text.toString().isNotBlank()
        val hasBookSelected = binding.editTextBookId.text.toString().isNotBlank()

        return hasUserSelected && hasBookSelected
    }
}