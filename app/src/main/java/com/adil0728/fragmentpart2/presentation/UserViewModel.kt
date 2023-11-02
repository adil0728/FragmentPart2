package com.adil0728.fragmentpart2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adil0728.fragmentpart2.data.UserListRepositoryImpl
import com.adil0728.fragmentpart2.domain.GetUserUC
import com.adil0728.fragmentpart2.domain.User

class UserViewModel: ViewModel() {

    private val repository = UserListRepositoryImpl()

    private val getUserUC = GetUserUC(repository)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getUser(userId: Int) {
        val item = getUserUC.getUser(userId)
        _user.value = item
    }
}