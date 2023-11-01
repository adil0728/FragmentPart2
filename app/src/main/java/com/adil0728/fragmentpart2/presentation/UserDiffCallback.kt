package com.adil0728.fragmentpart2.presentation

import androidx.recyclerview.widget.DiffUtil
import com.adil0728.fragmentpart2.model.User


class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}