package com.example.buy_it.ui.screens.followlist

import com.example.buy_it.data.dtos.UserDTO

data class FollowListState(
    val users: List<UserDTO> = emptyList()
)