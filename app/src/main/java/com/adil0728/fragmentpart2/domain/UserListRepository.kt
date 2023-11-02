package com.adil0728.fragmentpart2.domain

import androidx.lifecycle.LiveData

interface UserListRepository {

    fun getUser(userId: Int): User

    fun getUserList(): LiveData<List<User>>

    fun editUser(user:User)
}