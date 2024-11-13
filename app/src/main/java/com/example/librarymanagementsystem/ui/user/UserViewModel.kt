package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.UserRepository

class UserViewModel : ViewModel() {

    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = _users

    fun save(user: User) {
        if (user.id == null) {
            UserRepository.insert(user)
        } else {
            UserRepository.update(user)
        }

        updateUserList()
    }

    fun delete(user: User) = UserRepository.delete(user).also { success ->
        if (success) {
            updateUserList()
        }
    }

    fun updateUserList() {
        _users.value = UserRepository.getList()
    }
}
