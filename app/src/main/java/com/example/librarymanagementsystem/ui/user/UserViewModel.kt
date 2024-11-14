package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.user.UserInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserInterface
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = _users

    fun getUserList(): List<User> = userRepository.getList()

    fun save(user: User) {
        if (user.id == null) {
            userRepository.insert(user)
        } else {
            userRepository.update(user)
        }

        updateUserList()
    }

    fun delete(user: User) = userRepository.delete(user).also { success ->
        if (success) {
            updateUserList()
        }
    }

    fun updateUserList() {
        _users.value = userRepository.getList()
    }
}
