package com.example.buy_it.data.datasource.impl

import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.services.UserRetrofitService
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileDTO
import javax.inject.Inject

class UserRetrofitDatasourceImplementation @Inject constructor(private val service: UserRetrofitService): UserRemoteDatasource {
    override suspend fun getUserById(id: String): UserProfileDTO {
        return service.getUserById(id)
    }

    override suspend fun getUserReviews(id: String): List<ReviewDTO> {
        return service.getReviewsByUser(id)
    }
}