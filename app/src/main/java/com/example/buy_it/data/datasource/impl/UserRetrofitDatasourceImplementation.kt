package com.example.buy_it.data.datasource.impl

import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.services.UserRetrofitService
import com.example.buy_it.data.dtos.RegisterUserDto
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileRetrofitDTO
import javax.inject.Inject

class UserRetrofitDatasourceImplementation @Inject constructor(
    private val service: UserRetrofitService
) : UserRemoteDatasource {

    override suspend fun getUserById(id: String): UserProfileRetrofitDTO {
        return service.getUserById(id.toInt())
    }

    override suspend fun getUserReviews(id: String): List<ReviewDTO> {
        return service.getReviewsByUser(id)
    }

    override suspend fun registerUser(registerUserDto: RegisterUserDto, userId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserProfile(userId: String, name: String, pfpURL: String?) {
    }
}