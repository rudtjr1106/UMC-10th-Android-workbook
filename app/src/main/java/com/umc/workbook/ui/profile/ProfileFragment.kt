package com.umc.workbook.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.umc.workbook.data.NetworkClient
import com.umc.workbook.databinding.FragmentProfileBinding
import com.umc.workbook.ui.profile.adapter.ProfileFollowingAdapter
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val followingAdapter = ProfileFollowingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setupFollowingRecyclerView()
        fetchProfileUser()
        fetchFollowingUsers()
        return binding.root
    }

    private fun setupFollowingRecyclerView() {
        binding.recyclerFollowing.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = followingAdapter
        }
    }

    private fun fetchProfileUser() {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                NetworkClient.reqResApiService.getUser(1)
            }.onSuccess { response ->
                val user = response.data
                binding.apply {
                    textProfileNickname.text = getString(
                        com.umc.workbook.R.string.profile_name_format,
                        user.firstName,
                        user.lastName
                    )
                    textProfileEmail.text = user.email
                    imageProfileAvatar.load(user.avatar) {
                        transformations(CircleCropTransformation())
                    }
                }
            }
        }
    }

    private fun fetchFollowingUsers() {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                NetworkClient.reqResApiService.getUsers(page = 1)
            }.onSuccess { response ->
                followingAdapter.submitList(response.data)
            }
        }
    }
}
