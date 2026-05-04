package com.umc.workbook.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umc.workbook.data.model.ReqResUser
import com.umc.workbook.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val profileUser: ReqResUser? = null,
    val followingUsers: List<ReqResUser> = emptyList()
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun loadProfileUser(id: Int = 1) {
        viewModelScope.launch {
            runCatching { userRepository.getUser(id) }
                .onSuccess { user ->
                    _uiState.value = _uiState.value.copy(profileUser = user)
                }
        }
    }

    fun loadFollowingUsers(page: Int = 1) {
        viewModelScope.launch {
            runCatching { userRepository.getUsers(page) }
                .onSuccess { users ->
                    _uiState.value = _uiState.value.copy(followingUsers = users)
                }
        }
    }

    fun loadProfileData() {
        loadProfileUser(id = 1)
        loadFollowingUsers(page = 1)
    }
}
