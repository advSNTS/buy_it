package com.example.buy_it.ui.screens.profile

import com.example.buy_it.data.ReviewInfo

data class ProfileState(
    val reviews: List<ReviewInfo> = emptyList(),
    val productosCount: String = "0",
    val seguidoresCount: String = "0",
    val profileImage: String? = ""
)