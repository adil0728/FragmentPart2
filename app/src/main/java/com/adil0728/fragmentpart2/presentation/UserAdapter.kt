package com.adil0728.fragmentpart2.presentation

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adil0728.fragmentpart2.R
import com.adil0728.fragmentpart2.databinding.UserItemBinding
import com.adil0728.fragmentpart2.model.User
import com.squareup.picasso.Picasso

class UserAdapter(): ListAdapter<User, UserAdapter.UserViewHolder>(
    UserDiffCallback()
){

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener{
        fun onItemClick(user: User)
    }

    class UserViewHolder(binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val name = binding.name
        val secondName = binding.secondName
        val number = binding.number
        val photo = binding.userPhoto

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =  UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val userItem = getItem(position)
        holder.name.text = getItem(position).name
        holder.secondName.text = getItem(position).secondName
        holder.number.text = getItem(position).number
        Picasso.get().load(userItem.photoUrl)
            .fit()
            .placeholder(R.drawable.default_user)
            .into(holder.photo)
        holder.itemView.setOnClickListener {

            onItemClickListener?.onItemClick(userItem)
        }

    }


}