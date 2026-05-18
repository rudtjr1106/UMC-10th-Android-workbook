package com.umc.workbook.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.data.NetworkClient
import com.umc.workbook.model.ReqResUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val _profileUser = MutableStateFlow<ReqResUser?>(null)
    val profileUser: StateFlow<ReqResUser?> = _profileUser

    private val _followingUsers = MutableStateFlow<List<ReqResUser>>(emptyList())
    val followingUsers: StateFlow<List<ReqResUser>> = _followingUsers

    init {
        fetchProfileUser()
        fetchFollowingUsers()
    }

    private fun fetchProfileUser() {
        viewModelScope.launch {
            runCatching {
                NetworkClient.reqResApiService.getUser(1)
            }.onSuccess { response ->
                _profileUser.value = response.data
            }
        }
    }

    private fun fetchFollowingUsers() {
        viewModelScope.launch {
            runCatching {
                NetworkClient.reqResApiService.getUsers(page = 1)
            }.onSuccess { response ->
                _followingUsers.value = response.data
            }
        }
    }
}
