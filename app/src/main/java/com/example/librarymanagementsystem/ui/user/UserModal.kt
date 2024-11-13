package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.R
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.databinding.ModalUserBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.base.BaseModal

class UserModal : BaseModal() {

    companion object {
        private const val USER_ID = "id"
        private const val USER_NAME = "name"
        private const val USER_SURNAME = "surname"

        fun newInstance(user: User? = null) = UserModal().apply {
            arguments = Bundle().apply {
                putInt(USER_ID, user?.id ?: 0)
                putString(USER_NAME, user?.name)
                putString(USER_SURNAME, user?.surname)
            }
        }
    }

    private lateinit var binding: ModalUserBinding
    private val viewmodel by activityViewModels<UserViewModel>()
    private lateinit var userItem: User

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ModalUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userItem = getUserItem()

        setupUserData()

        binding.btnSave.setSafeOnClickListener {
            submitData()
            dismiss()
        }

        if (userItem.id == null) {
            binding.btnDelete.visibility = View.INVISIBLE
        } else {
            binding.btnDelete.visibility = View.VISIBLE

            binding.btnDelete.setSafeOnClickListener {
                if (!viewmodel.delete(userItem)) {
                    displayError(getString(R.string.user_delete_error))
                }

                dismiss()
            }
        }
    }

    private fun setupUserData() {
        binding.textFieldName.setText(userItem.name)
        binding.textFieldSurname.setText(userItem.surname)
    }

    private fun submitData() {
        if (!hasValidData()) {
            return displayError(getString(R.string.user_form_validation_error))
        }

        userItem.name = binding.textFieldName.text.toString()
        userItem.surname = binding.textFieldSurname.text.toString()

        viewmodel.save(userItem)
    }

    private fun hasValidData(): Boolean {
        val user = getUserItem()
        return !user.name.isNullOrEmpty() && !user.surname.isNullOrEmpty()
    }

    private fun getUserItem(): User = if (isEditingItem()) {
        User(
            id = arguments?.getInt(USER_ID),
            name = arguments?.getString(USER_NAME) ?: "",
            surname = arguments?.getString(USER_SURNAME) ?: "",
        )
    } else {
        User(
            name = binding.textFieldName.text.toString(),
            surname = binding.textFieldSurname.text.toString()
        )
    }

    private fun displayError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun isEditingItem() = (arguments?.getInt(USER_ID) ?: 0) > 0
}