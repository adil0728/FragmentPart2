package com.adil0728.fragmentpart2.presentation

import androidx.lifecycle.ViewModel
import com.adil0728.fragmentpart2.data.UserListRepositoryImpl
import com.adil0728.fragmentpart2.domain.GetUserListUC

class UserListViewModel: ViewModel() {

    private val repository = UserListRepositoryImpl()

    private val getUserListUC = GetUserListUC(repository)

    val userList = getUserListUC.getUserList()
}