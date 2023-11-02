package com.adil0728.fragmentpart2.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.adil0728.fragmentpart2.domain.User
import com.adil0728.fragmentpart2.domain.UserListRepository
import java.lang.RuntimeException

class UserListRepositoryImpl : UserListRepository {

    private val userListLD = MutableLiveData<List<User>>()
    private var userList = sortedSetOf<User>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 1

    init {
        for (i in 0 until 100) {
            var e = ""
            e = if (i < 10) {
                "00$i"
            } else {
                "0$i"
            }
            val user = User(
                "Name $i",
                "SecondName $i",
                "8${e}9999999",
                "https://www.w3schools.com/w3images/lights.jpg"
            )
            addUser(user)
            updateList()

        }
    }

    override fun getUser(userId: Int): User {
        return userList.find {
            it.id == userId
        } ?: throw RuntimeException("Element with id $userId not found")
    }

    override fun getUserList(): LiveData<List<User>> {
        return userListLD
    }

    private fun updateList() {
        userListLD.value = userList.toList()
    }

    override fun editUser(user: User) {
        val oldElement = getUser(user.id)
        userList.remove(oldElement)
        addUser(user)
    }

    private fun addUser(user: User) {
        if (user.id == User.UNDEFINED_ID) {
            user.id = autoIncrementId++
        }
        userList.add(user)
        updateList()
    }
}