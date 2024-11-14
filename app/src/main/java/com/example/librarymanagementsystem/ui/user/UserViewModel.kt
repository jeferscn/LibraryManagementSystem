package com.example.librarymanagementsystem.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.librarymanagementsystem.data.di.IoDispatcher
import com.example.librarymanagementsystem.data.model.User
import com.example.librarymanagementsystem.data.repository.user.UserInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val userRepository: UserInterface
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>(listOf())
    val users: LiveData<List<User>> = _users

    fun setup() {
        viewModelScope.launch {
            updateUserList()
        }
    }

    fun save(user: User) = viewModelScope.launch(ioDispatcher) {
        if (user.id == null) {
            userRepository.insert(user)
        } else {
            userRepository.update(user)
        }

        updateUserList()
    }

    fun delete(userId: Int?, callback: (Boolean) -> Unit) = viewModelScope.launch(ioDispatcher) {
        userRepository.delete(userId).also { success ->
            if (success) {
                updateUserList()
            }

            viewModelScope.launch {
                callback(success)
            }
        }
    }

    private suspend fun updateUserList() = withContext(ioDispatcher) {
        _users.postValue(userRepository.getList())
    }
}
