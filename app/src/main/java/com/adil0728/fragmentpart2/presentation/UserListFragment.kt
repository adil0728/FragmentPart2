package com.adil0728.fragmentpart2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adil0728.fragmentpart2.R
import com.adil0728.fragmentpart2.databinding.FragmentUserListBinding
import com.adil0728.fragmentpart2.model.User


class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userAdapter: UserAdapter
    private var userList: ArrayList<User> = ArrayList()
    private lateinit var userRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)

        addUser()
        initRecyclerView()
        return binding.root
    }

    private fun addUser() {
        for (i in 0 until 100) {
            var e = ""
            e = if (i < 10) {
                "00$i"
            } else {
                "0$i"
            }
            val user = User(
                i,
                "Name $i",
                "SecondName $i",
                "8${e}9999999",
                "https://www.w3schools.com/w3css/img_fjords.jpg"
            )
            userList.add(user)

        }
    }

    private fun initRecyclerView() {
        userRV = binding.userRv
        linearLayoutManager = LinearLayoutManager(activity)
        userRV.layoutManager = linearLayoutManager
        userAdapter = UserAdapter()
        userAdapter.submitList(userList)
        userAdapter.onItemClickListener = object : UserAdapter.OnItemClickListener {
            override fun onItemClick(user: User) {
                //    viewModel.deleteContact(contact)
            }
        }
        userRV.adapter = userAdapter
        userRV.setHasFixedSize(true)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        fun newInstance(param1: String, param2: String) =
            UserListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}