package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.models.User

class UserViewModel : ViewModel() {

    private val items = mutableListOf<User>()

    fun getItems(): MutableList<User> {
        return items
    }

    fun addItem(name: String, surname: String, failure: () -> Unit) {
        if (name.isNotEmpty() && surname.isNotEmpty()) {
            items.add(
                User(
                    id = items.size + 1,
                    name = name,
                    surname = surname
                )
            )
        } else {
            failure()
        }
    }

    fun removeItem(position: Int, failure: () -> Unit) {
        runCatching {
            items.removeAt(position)
        }.onFailure {
            failure()
        }
    }
}