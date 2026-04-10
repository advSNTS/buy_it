package com.example.buy_it.data.datasource.services

import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response

interface ReviewRetrofitService {

    @GET("reviews")
    suspend fun getAllReviews(): List<ReviewDTO>

    @GET("reviews/{id}")
    suspend fun getReviewById(
        @Path("id") id: String
    ): ReviewDTO

    @GET("users/{userId}/reviews")
    suspend fun getReviewsByUserId(
        @Path("userId") userId: String
    ): List<ReviewDTO>

    @POST("reviews")
    suspend fun createReview(
        @Body review: CreateReviewDTO
    )

    @PUT("reviews/{id}")
    suspend fun updateReview(
        @Path("id") id: String,
        @Body review: CreateReviewDTO
    )

    @DELETE("reviews/{id}")
    suspend fun deleteReview(
        @Path("id") id: String
    ): Response<Unit>
}