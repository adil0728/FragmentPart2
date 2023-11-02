package com.adil0728.fragmentpart2.domain

class EditUserUC(private val userListRepository: UserListRepository) {

    fun editUser(user:User) {
        return userListRepository.editUser(user)
    }
}
