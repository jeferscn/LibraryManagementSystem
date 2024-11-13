package com.example.librarymanagementsystem.ui.borrow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.Book
import com.example.librarymanagementsystem.data.model.Borrow
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.book.BookRepository
import com.example.librarymanagementsystem.data.repository.user.UserRepository
import com.example.librarymanagementsystem.databinding.ModalBorrowBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.base.BaseModal

class BorrowModal : BaseModal() {

    private lateinit var binding: ModalBorrowBinding

    private val viewmodel by activityViewModels<BorrowViewModel>()

    private lateinit var borrowItem: Borrow

    private val userList by lazy { UserRepository.getList() }
    private val bookList by lazy { BookRepository.getList() }

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

        setupAdapters()
        setupBorrowData()

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

    private fun setupAdapters() {
        val userAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            userList.map { it.toAdapterItem() }
        )

        val bookAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            bookList.map { it.toAdapterItem() }
        )

        binding.autoCompleteUserId.setAdapter(userAdapter)
        binding.autoCompleteBookId.setAdapter(bookAdapter)

        binding.autoCompleteUserId.setOnItemClickListener { _, _, position, _ ->
            borrowItem.userId = userList[position].id ?: 0
        }

        binding.autoCompleteBookId.setOnItemClickListener { _, _, position, _ ->
            borrowItem.bookId = bookList[position].id ?: 0
        }

        binding.autoCompleteUserId.setOnClickListener {
            binding.autoCompleteUserId.showDropDown()
        }
        binding.autoCompleteBookId.setOnClickListener {
            binding.autoCompleteBookId.showDropDown()
        }

        binding.autoCompleteUserId.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.autoCompleteUserId.showDropDown()
        }
        binding.autoCompleteBookId.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) binding.autoCompleteBookId.showDropDown()
        }
    }

    private fun setupBorrowData() {
        val book = BookRepository.find(borrowItem.bookId)
        val user = UserRepository.find(borrowItem.userId)

        if (book == null || user == null) {
            return
        }

        binding.autoCompleteUserId.setText(user.toAdapterItem(), false)
        binding.autoCompleteBookId.setText(book.toAdapterItem(), false)
    }

    private fun submitData() {
        if (!hasValidData()) {
            Toast.makeText(requireContext(), getString(R.string.borrow_form_validation_error), Toast.LENGTH_SHORT).show()
            return
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
        val hasUserSelected = binding.autoCompleteUserId.text.toString().isNotBlank()
        val hasBookSelected = binding.autoCompleteBookId.text.toString().isNotBlank()

        return hasUserSelected && hasBookSelected
    }

    private fun User.toAdapterItem(): String = "$id - $name"

    private fun Book.toAdapterItem(): String = "$id - $title"
}