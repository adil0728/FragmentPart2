package com.adil0728.fragmentpart2.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
data class User(
    val name: String = "",
    val secondName: String = "",
    val number: String = "",
    var photoUrl: String = "empty",
    var id: Int = UNDEFINED_ID,
) : Parcelable {
    companion object {
        const val UNDEFINED_ID = 0
    }

}

