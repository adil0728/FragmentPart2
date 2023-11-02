package com.adil0728.fragmentpart2.presentation

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import com.adil0728.fragmentpart2.R
import com.adil0728.fragmentpart2.databinding.FragmentEditUserInfoBinding
import com.adil0728.fragmentpart2.domain.User
import com.squareup.picasso.Picasso

class EditUserInfoFragment : Fragment() {

    private var _binding: FragmentEditUserInfoBinding? = null
    private val binding: FragmentEditUserInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentEditUserInfoBinding == null")

    private var userId: Int = User.UNDEFINED_ID
    private lateinit var viewModel: UserViewModel
    private  var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEditUserInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userPhoto.setOnClickListener {
            changePhotoUser()
        }

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        viewModel.getUser(userId)
        viewModel.user.observe(viewLifecycleOwner) {
            binding.etName.setText(it.name)
            binding.etSecondName.setText(it.secondName)
            binding.etNumber.setText(it.number)
            Picasso.get().load(it.photoUrl).fit().placeholder(R.drawable.default_user)
                .into(binding.userPhoto)

            binding.saveButton.setOnClickListener {
                val user = User(
                    binding.etName.text.toString(),
                    binding.etSecondName.text.toString(),
                    binding.etNumber.text.toString(),
                    uri.toString(),
                    userId
                )
                setFragmentResult(
                    UserListFragment.REQUEST_KEY, bundleOf(UserListFragment.EXTRA_KEY to user)
                )
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        userId = args.getInt(USER_ITEM_ID, User.UNDEFINED_ID)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changePhotoUser() {
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)
        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK) {
            uri = imageReturnedIntent?.data
          binding.userPhoto.setImageURI(uri)
        }
    }

    companion object {

        private const val GALLERY_REQUEST = 1
        private const val USER_ITEM_ID = "extra_user_item_id"

        fun newInstanceEditUserInfoFragment(userId: Int) = EditUserInfoFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ITEM_ID, userId)
            }
        }
    }
}