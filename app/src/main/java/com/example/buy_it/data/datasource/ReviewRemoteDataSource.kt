package com.example.buy_it.data.datasource

import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO

interface ReviewRemoteDataSource {

    suspend fun getAllReviews(): List<ReviewDTO>

    suspend fun getReviewById(id: String): ReviewDTO

    suspend fun getReviewsByUserId(userId: String): List<ReviewDTO>

    suspend fun createReview(review: CreateReviewDTO)

    suspend fun updateReview(id: String, review: CreateReviewDTO)

    suspend fun deleteReview(id: String)
}