package com.adil0728.fragmentpart2.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adil0728.fragmentpart2.data.UserListRepositoryImpl
import com.adil0728.fragmentpart2.domain.EditUserUC
import com.adil0728.fragmentpart2.domain.GetUserListUC
import com.adil0728.fragmentpart2.domain.User

class UserListViewModel: ViewModel() {

    private val repository = UserListRepositoryImpl()

    private val getUserListUC = GetUserListUC(repository)
    private val editUserUC = EditUserUC(repository)


    val userList = getUserListUC.getUserList()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun editShopItem(user: User) {
        editUserUC.editUser(user)
        }
    }
