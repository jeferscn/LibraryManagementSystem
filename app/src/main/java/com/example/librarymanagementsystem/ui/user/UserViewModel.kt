package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val items = mutableListOf(
        Pair("Title 1", "Description 1"),
        Pair("Title 2", "Description 2")
    )

    fun getItems(): MutableList<Pair<String, String>> {
        return items
    }

    fun addItem(title: String, description: String, failure: () -> Unit) {
        if (title.isNotEmpty() && description.isNotEmpty()) {
            items.add(Pair(title, description))
        } else {
            failure()
        }
    }
}