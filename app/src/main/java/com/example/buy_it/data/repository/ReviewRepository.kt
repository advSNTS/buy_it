package com.example.buy_it.data.repository

import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.datasource.implementation.ReviewRetrofitDataSourceImplementation
import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.toReviewInfo
import retrofit2.HttpException
import javax.inject.Inject

class ReviewRepository @Inject constructor(
    private val remoteDataSource: ReviewRetrofitDataSourceImplementation
) {

    suspend fun getReviewById(id: String): Result<ReviewInfo> {
        return try {
            val review = remoteDataSource.getReviewById(id).toReviewInfo()
            Result.success(review)
        } catch (e: HttpException) {
            Result.failure(Exception("Error HTTP ${e.code()} al cargar la reseña"))
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo cargar la reseña"))
        }
    }

    suspend fun createReview(
        productId: String,
        like: Boolean,
        comment: String,
        userId: String = "1"
    ): Result<Unit> {
        return try {
            val dto = CreateReviewDTO(
                userId = userId.toInt(),
                productId = mapProductIdToBackendId(productId),
                like = like,
                comment = comment
            )
            remoteDataSource.createReview(dto)
            Result.success(Unit)
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.failure(Exception("Error HTTP ${e.code()} al crear la reseña"))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("No se pudo crear la reseña: ${e.message}"))
        }
    }

    suspend fun updateReview(
        reviewId: String,
        productId: String,
        like: Boolean,
        comment: String,
        userId: String = "1"
    ): Result<Unit> {
        return try {
            val dto = CreateReviewDTO(
                userId = userId.toInt(),
                productId = mapProductIdToBackendId(productId),
                like = like,
                comment = comment
            )
            remoteDataSource.updateReview(reviewId, dto)
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(Exception("Error HTTP ${e.code()} al actualizar la reseña"))
        } catch (e: Exception) {
            Result.failure(Exception("No se pudo actualizar la reseña"))
        }
    }

    suspend fun deleteReview(reviewId: String): Result<Unit> {
        return try {
            remoteDataSource.deleteReview(reviewId)
            Result.success(Unit)
        } catch (e: HttpException) {
            e.printStackTrace()
            Result.failure(Exception("Error HTTP ${e.code()} al eliminar la reseña"))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(Exception("No se pudo eliminar la reseña: ${e.message}"))
        }
    }

    suspend fun getReviewsByProductId(productId: String): Result<List<ReviewInfo>> {
        return try {
            val backendProductId = mapProductIdToBackendId(productId)

            val reviews = remoteDataSource.getAllReviews()
                .filter { it.productId == backendProductId }
                .map { it.toReviewInfo() }

            Result.success(reviews)
        } catch (e: HttpException) {
            Result.failure(Exception("Error HTTP ${e.code()} al cargar las reseñas"))
        } catch (e: Exception) {
            Result.failure(Exception("No se pudieron cargar las reseñas"))
        }
    }

    private fun mapProductIdToBackendId(productId: String): Int {
        return when (productId) {
            "rey_300g" -> 1
            else -> 1
        }
    }
}