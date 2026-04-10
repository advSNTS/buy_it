package com.example.buy_it.data.datasource

import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileDTO

interface UserRemoteDatasource {

    suspend fun getUserById(id: String): UserProfileDTO
    suspend fun getUserReviews(id: String): List<ReviewDTO>
}