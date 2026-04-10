package com.example.buy_it.data.repository

import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.UserProfileInfo

import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.dtos.toReviewInfo
import com.example.buy_it.data.dtos.toUserProfileInfo
import retrofit2.HttpException
import javax.inject.Inject

class UserRepository @Inject constructor(private val userRetrofitDatasourceImplementation: UserRetrofitDatasourceImplementation) {

    suspend fun getUserById(id: String): Result<UserProfileInfo>{
        return try{
            val user = userRetrofitDatasourceImplementation.getUserById(id)
            val userProfileInfo = user.toUserProfileInfo()
            Result.success(userProfileInfo)
        } catch (e: HttpException){
            Result.failure(e)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getUserReviews(userId: String): Result<List<ReviewInfo>>{
        return try{
            val reviews = userRetrofitDatasourceImplementation.getUserReviews(userId)
            val reviewInfo = reviews.map { it.toReviewInfo() }
            Result.success(reviewInfo)
        } catch (e: Exception){
            Result.failure(e)
        }
    }
}