package com.adil0728.fragmentpart2.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adil0728.fragmentpart2.R
import com.adil0728.fragmentpart2.databinding.FragmentUserListBinding
import com.adil0728.fragmentpart2.domain.User


class UserListFragment : Fragment() {
    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding
        get() = _binding ?: throw RuntimeException("FragmentUserListBinding == null")

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var userAdapter: UserAdapter
    private var userList: ArrayList<User> = ArrayList()
    private lateinit var userRV: RecyclerView

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        viewModel = ViewModelProvider(this)[UserListViewModel::class.java]
        viewModel.userList.observe(viewLifecycleOwner){
            userAdapter.submitList(it)
        }
        userAdapter.onItemClickListener = {
            launchFragment(EditUserInfoFragment.newInstanceEditUserInfoFragment(it.id))
        }
    }

    private fun launchFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initRecyclerView() {
        userRV = binding.userRv
        linearLayoutManager = LinearLayoutManager(activity)
        userRV.layoutManager = linearLayoutManager
        userAdapter = UserAdapter()
        userAdapter.submitList(userList)
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