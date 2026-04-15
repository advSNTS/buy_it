package com.example.buy_it.data.repository

import android.util.Log
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.UserProfileInfo
import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.impl.firestore.UserFirestoreDataSourceImpl
import com.example.buy_it.data.dtos.RegisterUserDto
import com.example.buy_it.data.dtos.toReviewInfo
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRemoteDatasource: UserFirestoreDataSourceImpl) {

    suspend fun getUserById(id: String): Result<UserProfileInfo> {
        return try {
            val user = userRemoteDatasource.getUserById(id)
            val userProfileInfo = user.toUserProfileInfo()
            Result.success(userProfileInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserReviews(userId: String): Result<List<ReviewInfo>> {
        return try {
            val reviews = userRemoteDatasource.getUserReviews(userId)
            val reviewInfo = reviews.map { it.toReviewInfo() }
            Result.success(reviewInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun registerUser(username: String, userId: String): Result<Unit> {
        return try {
            val registerUserDto = RegisterUserDto(username)
            userRemoteDatasource.registerUser(registerUserDto, userId)
            Log.d("TAG", "usuario registrado, id: $userId")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.d("TAG", "error en register repository, id: ${e.message}")
            Result.failure(e)
        }
    }
}