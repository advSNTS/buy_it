package com.example.buy_it.data.dtos

import com.example.buy_it.data.UserProfileInfo
import java.time.LocalDate
import kotlin.toString

abstract class UserDtoGeneric{
    abstract fun toUserProfileInfo(): UserProfileInfo
}

data class UserProfileFirestoreDTO(
    val id: Int,
    val username: String,
    val name: String,
    val pfpURL: String?,
    val biography: String,
    val created: String?,
    val email: String,
    val password: String,
    val followersCount: Int
): UserDtoGeneric(){
    constructor() : this(0, "", "", "", "", "", "", "", 0)

    override fun toUserProfileInfo(): UserProfileInfo {
        return UserProfileInfo(
            id = id.toString(),
            username = username,
            name = name,
            pfpURL = pfpURL ?: "",
            biography = biography,
            createdAt = if (!created.isNullOrEmpty()) LocalDate.parse(created.substring(0, 10)) else LocalDate.now(),
            email = email,
            password = password,
            followersCount = followersCount

        )
    }
}

data class UserProfileRetrofitDTO(
    val id: Int,
    val username: String,
    val name: String,
    val pfpURL: String?,
    val biography: String,
    val created: String?,
    val email: String,
    val password: String,
    val followersCount: Int
): UserDtoGeneric(){
    constructor() : this(0, "", "", "", "", "", "", "", 0)

    override fun toUserProfileInfo(): UserProfileInfo {
        return UserProfileInfo(
            id = id.toString(),
            username = username,
            name = name,
            pfpURL = pfpURL ?: "",
            biography = biography,
            createdAt = if (!created.isNullOrEmpty()) LocalDate.parse(created.substring(0, 10)) else LocalDate.now(),
            email = email,
            password = password,
            followersCount = followersCount

        )
    }
}


