package com.example.buy_it.data.datasource.implementation

import com.example.buy_it.data.datasource.ReviewRemoteDataSource
import com.example.buy_it.data.datasource.services.ReviewRetrofitService
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO
import javax.inject.Inject

class ReviewRetrofitDataSourceImplementation @Inject constructor(
    private val service: ReviewRetrofitService
) : ReviewRemoteDataSource {

    override suspend fun getAllReviews(): List<ReviewDTO> =
        service.getAllReviews()

    override suspend fun getReviewById(id: String): ReviewDTO =
        service.getReviewById(id)

    override suspend fun getReviewsByUserId(userId: String): List<ReviewDTO> =
        service.getReviewsByUserId(userId)

    override suspend fun createReview(review: CreateReviewDTO) =
        service.createReview(review)

    override suspend fun updateReview(id: String, review: CreateReviewDTO) =
        service.updateReview(id, review)

    override suspend fun deleteReview(id: String) {
        val response = service.deleteReview(id)
        if (!response.isSuccessful) {
            throw retrofit2.HttpException(response)
        }
    }
}