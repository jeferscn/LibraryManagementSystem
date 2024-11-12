package com.example.librarymanagementsystem.ui.activity.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.model.User

class UserViewModel : ViewModel() {

    val users = MutableLiveData<MutableList<User>>(mutableListOf())

    fun getItems(): MutableList<User> {
        return users.value ?: mutableListOf()
    }

    fun addItem(name: String, surname: String, failure: () -> Unit) {
        if (name.isNotEmpty() && surname.isNotEmpty()) {
            val updatedList = users.value ?: mutableListOf()
            updatedList.add(
                User(
                    id = updatedList.size + 1,
                    name = name,
                    surname = surname
                )
            )
            users.value = updatedList
        } else {
            failure()
        }
    }

    fun removeItem(position: Int, failure: () -> Unit) {
        runCatching {
            val updatedList = users.value ?: mutableListOf()
            updatedList.removeAt(position)
            users.value = updatedList
        }.onFailure {
            failure()
        }
    }
}
