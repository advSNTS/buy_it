package com.example.buy_it.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.buy_it.data.datasource.local.ReviewProvider
import com.example.buy_it.data.repository.AuthRepository
import com.example.buy_it.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        ProfileState(
            profileImage = authRepository.currentUser?.photoUrl?.toString()
        )
    )
    val uiState: StateFlow<ProfileState> = _uiState

    fun getUserProfile(userId: String) {
        if (userId.isBlank()) {
            android.util.Log.e("ProfileViewModel", "ERROR: Se intentó cargar un perfil con ID vacío")
            return
        }
        viewModelScope.launch {
            val result = userRepository.getUserById(userId)
            if (result.isSuccess) {
                val userProfileInfo = result.getOrNull()
                if (userProfileInfo != null) {
                    val isCurrentUser = userId == authRepository.currentUser?.uid
                    val profileImageUrl = if (isCurrentUser) {
                        authRepository.currentUser?.photoUrl?.toString() ?: userProfileInfo.pfpURL
                    } else {
                        userProfileInfo.pfpURL
                    }

                    _uiState.value = _uiState.value.copy(
                        user = userProfileInfo,
                        isCurrentUser = isCurrentUser,
                        memberSince = "Desde ${userProfileInfo.createdAt.year}",
                        profileImage = profileImageUrl.ifEmpty { null },
                        seguidoresCount = userProfileInfo.followersCount.toString(),
                        siguiendoCount = userProfileInfo.followingCount.toString(),
                        isFollowing = userProfileInfo.followed
                    )
                }
            } else {
                android.util.Log.e("ProfileViewModel", "getUserProfile error: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    fun getUserReviews(userId: String) {
        viewModelScope.launch {
            val result = userRepository.getUserReviews(userId)
            if (result.isSuccess) {
                _uiState.value = _uiState.value.copy(reviews = result.getOrNull()!!)
            } else {
                android.util.Log.e("ProfileViewModel", "getUserReviews error: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    fun followOrUnfollowUser() {
        val targetUserId = _uiState.value.user?.id ?: return

        viewModelScope.launch {
            val wasFollowing = _uiState.value.isFollowing
            val currentFollowers = _uiState.value.seguidoresCount.toIntOrNull() ?: 0

            val result = userRepository.followOrUnfollowUser(targetUserId)

            if (result.isSuccess) {
                _uiState.update { state ->
                    state.copy(
                        isFollowing = !wasFollowing,
                        seguidoresCount = if (wasFollowing) {
                            (currentFollowers - 1).coerceAtLeast(0).toString()
                        } else {
                            (currentFollowers + 1).toString()
                        },
                        user = state.user?.copy(
                            followed = !wasFollowing,
                            followersCount = if (wasFollowing) {
                                (currentFollowers - 1).coerceAtLeast(0)
                            } else {
                                currentFollowers + 1
                            }
                        )
                    )
                }
            } else {
                android.util.Log.e(
                    "ProfileViewModel",
                    "followOrUnfollow error: ${result.exceptionOrNull()?.message}"
                )
            }
        }
    }

    init {
    }
}