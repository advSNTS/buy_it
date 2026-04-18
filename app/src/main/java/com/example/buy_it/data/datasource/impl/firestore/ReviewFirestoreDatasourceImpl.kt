package com.example.buy_it.data.datasource.impl.firestore

import android.util.Log
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
        val snapshot = db.collection("reviews").get().await()
        Log.d("reviews-firestore", "Total reviews: ${snapshot.size()}")

        return snapshot.documents.mapNotNull { doc ->
            val dto = doc.toObject(ReviewDTO::class.java)
            Log.d("reviews-firestore", "Doc ${doc.id}: $dto")
            dto?.copy(id = doc.id)
        }
    }

    override suspend fun getReviewById(id: String): ReviewDTO {
        val doc = db.collection("reviews").document(id).get().await()
        val dto = doc.toObject(ReviewDTO::class.java)
            ?: throw Exception("Review no encontrada")
        return dto.copy(id = doc.id)
    }

    override suspend fun getReviewsByUserId(userId: String): List<ReviewDTO> {
        val snapshot = db.collection("reviews")
            .whereEqualTo("userId", userId)
            .get()
            .await()

        Log.d("reviews-firestore", "Reviews por userId=$userId: ${snapshot.size()}")

        return snapshot.documents.mapNotNull { doc ->
            val dto = doc.toObject(ReviewDTO::class.java)
            Log.d("reviews-firestore", "Doc ${doc.id}: $dto")
            dto?.copy(id = doc.id)
        }
    }

    override suspend fun getReviewsByProductId(productId: String): List<ReviewDTO> {
        val snapshot = db.collection("reviews")
            .whereEqualTo("productId", productId)
            .get()
            .await()

        Log.d("reviews-firestore", "Reviews por productId=$productId: ${snapshot.size()}")

        return snapshot.documents.mapNotNull { doc ->
            val dto = doc.toObject(ReviewDTO::class.java)
            Log.d("reviews-firestore", "Doc ${doc.id}: $dto")
            dto?.copy(id = doc.id)
        }
    }

    override suspend fun createReview(review: CreateReviewDTO) {
        db.collection("reviews")
            .add(review)
            .await()
    }

    override suspend fun updateReview(
        id: String,
        review: CreateReviewDTO
    ) {
        db.collection("reviews")
            .document(id)
            .set(review)
            .await()
    }

    override suspend fun deleteReview(id: String) {
        db.collection("reviews")
            .document(id)
            .delete()
            .await()
    }
}