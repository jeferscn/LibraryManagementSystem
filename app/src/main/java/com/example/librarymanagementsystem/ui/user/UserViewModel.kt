package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.models.User

class UserViewModel : ViewModel() {

    private val users = mutableListOf<User>()

    fun getItems(): MutableList<User> {
        return users
    }

    fun addItem(name: String, surname: String, failure: () -> Unit) {
        if (name.isNotEmpty() && surname.isNotEmpty()) {
            users.add(
                User(
                    id = users.size + 1,
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
            users.removeAt(position)
        }.onFailure {
            failure()
        }
    }
}