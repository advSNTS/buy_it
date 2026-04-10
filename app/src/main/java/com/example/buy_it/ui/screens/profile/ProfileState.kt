package com.example.buy_it.ui.screens.profile

import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.UserProfileInfo

data class ProfileState(
    val reviews: List<ReviewInfo> = emptyList(),
    val seguidoresCount: String = "0",
    val memberSince: String = "",
    val profileImage: String? = "",
    val user: UserProfileInfo? = null,
    val isCurrentUser: Boolean = false
)