package com.example.buy_it.data.dtos

import com.example.buy_it.data.UserProfileInfo
import java.time.LocalDate

data class UserProfileDTO(
    val id: Int,
    val username: String,
    val name: String,
    val pfpURL: String?,
    val biography: String,
    val createdAt: String,
    val email: String,
    val password: String,
    val followersCount: Int
)

fun UserProfileDTO.toUserProfileInfo(): UserProfileInfo{
    return UserProfileInfo(
        id = id.toString(),
        username = username,
        name = name,
        pfpURL = pfpURL ?: "",
        biography = biography,
        createdAt = LocalDate.parse(createdAt.substring(0, 10)),
        email = email,
        password = password,
        followersCount = followersCount

    )
}