package com.adil0728.fragmentpart2.model

import java.io.Serializable

data class User(
    var id: Int = 0,
    val name: String = "",
    val secondName: String = "",
    val number: String = "",
    var photoUrl: String = "empty",
    ): Serializable
