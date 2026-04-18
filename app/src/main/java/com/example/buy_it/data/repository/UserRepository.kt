package com.example.buy_it.data.repository

import android.util.Log
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.UserProfileInfo
import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.datasource.impl.firestore.UserFirestoreDataSourceImpl
import com.example.buy_it.data.dtos.RegisterUserDto
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userRemoteDatasource: UserFirestoreDataSourceImpl,
    private val reviewRepository: ReviewRepository
) {

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
        return reviewRepository.getReviewsByUserId(userId)
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

    suspend fun updateUserProfile(
        userId: String,
        name: String,
        pfpURL: String?
    ): Result<Unit> {
        return try {
            userRemoteDatasource.updateUserProfile(userId, name, pfpURL)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}