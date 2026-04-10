package com.example.buy_it.data.datasource.services

import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRetrofitService {
    @GET("users/{userId}/reviews")
    suspend fun getReviewsByUser(@Path("userId") userId: String): List<ReviewDTO>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: String): UserProfileDTO
}