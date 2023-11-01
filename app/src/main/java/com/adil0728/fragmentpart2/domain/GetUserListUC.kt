package com.adil0728.fragmentpart2.domain

import androidx.lifecycle.LiveData

class GetUserListUC(private val userListRepository: UserListRepository) {

    fun getUserList(): LiveData<List<User>>{
        return userListRepository.getUserList()
    }
}
