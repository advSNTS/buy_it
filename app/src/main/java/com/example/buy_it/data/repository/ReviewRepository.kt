package com.example.buy_it.data.repository

import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.datasource.services.ReviewRetrofitService
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.toReviewInfo
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewService: ReviewRetrofitService
) {
    suspend fun getReviews(): Result<List<ReviewInfo>> {
        return try {
            val reviews = reviewService.getAllReviews()
            Result.success(reviews.map { it.toReviewInfo() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReviewById(id: String): Result<ReviewInfo> {
        return try {
            val review = reviewService.getReviewById(id)
            Result.success(review.toReviewInfo())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createReview(productId: String, like: Boolean, comment: String): Result<Unit> {
        return try {
            val createReviewDTO = CreateReviewDTO(
                userID = "1", // Por practicidad según lo solicitado
                productId = productId,
                like = like,
                comment = comment
            )
            reviewService.createReview(createReviewDTO)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateReview(id: String, productId: String, like: Boolean, comment: String): Result<Unit> {
        return try {
            val createReviewDTO = CreateReviewDTO(
                userID = "1",
                productId = productId,
                like = like,
                comment = comment
            )
            reviewService.updateReview(id, createReviewDTO)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteReview(id: String): Result<Unit> {
        return try {
            reviewService.deleteReview(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReviewsByUserId(userId: String): Result<List<ReviewInfo>> {
        return try {
            val reviews = reviewService.getReviewsByUserId(userId)
            Result.success(reviews.map { it.toReviewInfo() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}