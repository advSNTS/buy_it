package com.example.buy_it.data

import java.time.LocalDate

data class UserProfileInfo(
    val id: String,
    val username: String,
    val name: String,
    val pfpURL: String,
    val biography: String,
    val createdAt: LocalDate,
    val email: String,
    val password: String,
    val followersCount: Int
)
