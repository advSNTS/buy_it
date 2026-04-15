package com.example.buy_it.data.datasource

import androidx.privacysandbox.ads.adservices.adid.AdId
import com.example.buy_it.data.dtos.RegisterUserDto
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserDtoGeneric

interface UserRemoteDatasource {

    suspend fun getUserById(id: String): UserDtoGeneric
    suspend fun getUserReviews(id: String): List<ReviewDTO>
    suspend fun registerUser(registerUserDto: RegisterUserDto, userId: String): Unit
}