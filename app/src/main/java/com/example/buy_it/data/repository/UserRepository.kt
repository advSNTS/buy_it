package com.example.buy_it.data.repository

import android.util.Log
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.UserProfileInfo
import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.datasource.impl.firestore.UserFirestoreDataSourceImpl
import com.example.buy_it.data.dtos.RegisterUserDto
import javax.inject.Inject
import com.example.buy_it.data.datasource.AuthRemoteDataSource

class UserRepository @Inject constructor(
    private val userRemoteDatasource: UserFirestoreDataSourceImpl,
    private val reviewRepository: ReviewRepository,
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    suspend fun getUserById(id: String): Result<UserProfileInfo> {
        return try {
            val currentUserId = authRemoteDataSource.currentUser?.uid
            val user = userRemoteDatasource.getUserById(id, currentUserId)
            val userProfileInfo = user.toUserProfileInfo()
            Result.success(userProfileInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserReviews(userId: String): Result<List<ReviewInfo>> {
        return reviewRepository.getReviewsByUserId(userId)
    }

    suspend fun registerUser(username: String, name: String, userId: String): Result<Unit> {
        return try {
            val registerUserDto = RegisterUserDto(username, name)
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

    suspend fun followOrUnfollowUser(targetUserId: String): Result<Unit> {
        return try {
            val currentUserId = authRemoteDataSource.currentUser?.uid
                ?: throw Exception("No hay un usuario autenticado")

            userRemoteDatasource.followOrUnfollowUser(
                currentUserId = currentUserId,
                targetUserId = targetUserId
            )

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getFollowingIds(): Result<List<String>> {
        return try {
            val currentUserId = authRemoteDataSource.currentUser?.uid
                ?: throw Exception("No hay un usuario autenticado")

            val followingIds = userRemoteDatasource.getFollowingIds(currentUserId)

            Result.success(followingIds)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}