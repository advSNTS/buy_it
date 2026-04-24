package com.example.buy_it.ui.screens.followlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buy_it.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FollowListViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FollowListState())
    val uiState: StateFlow<FollowListState> = _uiState

    fun loadUsers(userId: String, type: String) {
        viewModelScope.launch {
            val result = if (type == "followers") {
                userRepository.getFollowers(userId)
            } else {
                userRepository.getFollowing(userId)
            }

            if (result.isSuccess) {
                _uiState.value = FollowListState(
                    users = result.getOrDefault(emptyList())
                )
            }
        }
    }
}