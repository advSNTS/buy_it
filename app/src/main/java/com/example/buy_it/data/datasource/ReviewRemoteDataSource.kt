package com.example.buy_it.data.datasource

import com.example.buy_it.data.ReviewInfo

interface ReviewRemoteDataSource {
    suspend fun getAllTweets(): List<ReviewInfo>
}