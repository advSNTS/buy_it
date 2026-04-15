package com.example.buy_it.data.datasource.impl.firestore

import com.example.buy_it.data.datasource.ReviewRemoteDataSource
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ReviewFirestoreDatasourceImpl @Inject constructor(
    private val db: FirebaseFirestore
) : ReviewRemoteDataSource {
    override suspend fun getAllReviews(): List<ReviewDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun getReviewById(id: String): ReviewDTO {
        TODO("Not yet implemented")
    }

    override suspend fun getReviewsByUserId(userId: String): List<ReviewDTO> {
        TODO("Not yet implemented")
    }

    override suspend fun createReview(review: CreateReviewDTO) {
        db.collection("reviews").add(review).await()
    }

    override suspend fun updateReview(
        id: String,
        review: CreateReviewDTO
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteReview(id: String) {
        TODO("Not yet implemented")
    }
}