package com.adil0728.fragmentpart2.domain

import java.io.Serializable

data class User(
    var id: Int = UNDEFINED_ID,
    val name: String = "",
    val secondName: String = "",
    val number: String = "",
    var photoUrl: String = "empty",
) {
    companion object {
        const val UNDEFINED_ID = 0
    }

}

