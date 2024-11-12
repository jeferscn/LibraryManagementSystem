package com.example.librarymanagementsystem.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.librarymanagementsystem.databinding.ModalUserBinding
import com.example.librarymanagementsystem.extension.setSafeOnClickListener
import com.example.librarymanagementsystem.ui.base.BaseModal

class UserModal : BaseModal() {

    private lateinit var binding: ModalUserBinding
    private val viewmodel by activityViewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ModalUserBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setSafeOnClickListener {
            val name = binding.textFieldName.text.toString()
            val surname = binding.textFieldSurname.text.toString()

            if (name.isNotEmpty() && surname.isNotEmpty()) {
                viewmodel.addItem(
                    name = name,
                    surname = surname
                ) {}

                dismiss()
            }
        }
    }
}