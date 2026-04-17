package com.example.buy_it.data.datasource.services

import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileRetrofitDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserRetrofitService {
    @GET("users/{userId}/reviews")
    suspend fun getReviewsByUser(@Path("userId") userId: String): List<ReviewDTO>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Int): UserProfileRetrofitDTO

    @POST("users")
    suspend fun createUser(@Body user: UserProfileRetrofitDTO)
}