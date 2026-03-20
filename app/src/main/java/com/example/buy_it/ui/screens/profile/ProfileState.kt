package com.example.buy_it.ui.screens.profile

import com.example.buy_it.data.ProfileItems

data class ProfileState(
    val profileItems: List<ProfileItems> = emptyList(),
    val productosCount: String = "0",
    val seguidoresCount: String = "0",
    val seguidosCount: String = "0",
    val profileImage: String? = ""
)