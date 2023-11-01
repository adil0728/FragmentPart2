package com.adil0728.fragmentpart2.domain

class GetUserUC(private val userListRepository: UserListRepository) {

    fun getUser(userId: Int): User {
        return userListRepository.getUser(userId)
    }
}
