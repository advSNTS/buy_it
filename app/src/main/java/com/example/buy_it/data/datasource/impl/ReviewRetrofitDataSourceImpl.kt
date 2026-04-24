package com.example.buy_it.data.datasource.impl

import android.util.Log
import com.example.buy_it.data.datasource.ReviewRemoteDataSource
import com.example.buy_it.data.datasource.services.ReviewRetrofitService
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO
import javax.inject.Inject

class ReviewRetrofitDataSourceImplementation @Inject constructor(
    private val service: ReviewRetrofitService
) : ReviewRemoteDataSource {

    override suspend fun getAllReviews() =
        service.getAllReviews()

    override suspend fun getReviewById(id: String) =
        service.getReviewById(id)

    override suspend fun getReviewsByUserId(userId: String): List<ReviewDTO> =
        service.getReviewsByUserId(userId)

    override suspend fun getReviewsByProductId(productId: String): List<ReviewDTO> {
        Log.d("reviews_debug", "Retrofit enviando id=$productId")
        return service.getProductReviews(productId)
    }

    override suspend fun createReview(review: CreateReviewDTO) =
        service.createReview(review)

    override suspend fun updateReview(id: String, review: CreateReviewDTO) =
        service.updateReview(id, review)

    override suspend fun deleteReview(id: String) {
        service.deleteReview(id)
    }

    override suspend fun getReviewsByProductId(
        productId: String,
        currentUserId: String?
    ): List<ReviewDTO> {
        return getReviewsByProductId(productId)
    }

    override suspend fun sendReviewLike(reviewId: String, userId: String) {
    }

    override suspend fun getReviewsByUserIds(userIds: List<String>): List<ReviewDTO> {
        return emptyList()
    }

    override fun listenReviewsByProductId(
        productId: String,
        currentUserId: String?
    ): kotlinx.coroutines.flow.Flow<List<ReviewDTO>> {
        return kotlinx.coroutines.flow.flowOf(emptyList())
    }
}