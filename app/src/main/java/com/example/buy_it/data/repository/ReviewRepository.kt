package com.example.buy_it.data.repository

import android.util.Log
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.example.buy_it.data.datasource.impl.ReviewRetrofitDataSourceImplementation
import com.example.buy_it.data.datasource.impl.firestore.ReviewFirestoreDatasourceImpl
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.toReviewInfo
import retrofit2.HttpException
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val reviewRemoteDataSource: ReviewRetrofitDataSourceImplementation,
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun getReviews(): Result<List<ReviewInfo>> {
        return try {
            val reviewDTOs = reviewRemoteDataSource.getAllReviews()
            val reviewsInfo = reviewDTOs.map { it.toReviewInfo() }
            Result.success(reviewsInfo)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReviewById(id: String): Result<ReviewInfo> {
        return try {
            val reviewDTO = reviewRemoteDataSource.getReviewById(id)
            Result.success(reviewDTO.toReviewInfo())
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReviewsByUserId(userId: String): Result<List<ReviewInfo>> {
        return try {
            val reviewDTOs = reviewRemoteDataSource.getReviewsByUserId(userId)
            val reviewsInfo = reviewDTOs.map { it.toReviewInfo() }
            Result.success(reviewsInfo)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createReview(
        productId: String,
        like: Boolean,
        comment: String
    ): Result<Unit> {
        return try {
            val currentUserId = authRemoteDataSource.currentUser?.uid
                ?: throw Exception("No hay un usuario autenticado")

            val reviewDTO = CreateReviewDTO(
                userId = currentUserId,
                productId = productId,
                like = like,
                comment = comment
            )

            reviewRemoteDataSource.createReview(reviewDTO)
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateReview(
        id: String,
        productId: String,
        like: Boolean,
        comment: String
    ): Result<Unit> {
        return try {
            val currentUserId = authRemoteDataSource.currentUser?.uid
                ?: throw Exception("No hay un usuario autenticado")

            val reviewDTO = CreateReviewDTO(
                userId = currentUserId,
                productId = productId,
                like = like,
                comment = comment
            )

            reviewRemoteDataSource.updateReview(id, reviewDTO)
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteReview(id: String): Result<Unit> {
        return try {
            reviewRemoteDataSource.deleteReview(id)
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getReviewsByProductId(productId: String): Result<List<ReviewInfo>> {
        return try {
            val reviewDTOs = reviewRemoteDataSource.getReviewsByProductId(productId)
            val reviewsInfo = reviewDTOs.map { it.toReviewInfo() }
            Log.d("reviews", "Id: $productId, Reviews: $reviewsInfo")
            Result.success(reviewsInfo)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}