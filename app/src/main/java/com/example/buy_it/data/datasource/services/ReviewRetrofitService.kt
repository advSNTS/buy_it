package com.example.buy_it.data.datasource.services

import com.example.buy_it.data.dtos.ReviewDTO
import retrofit2.http.GET
import retrofit2.http.POST


interface ReviewRetrofitService {

    @GET("reviews")
    suspend fun getAllTweets(): List<ReviewDTO>

    @POST("reviews")
    suspend fun postTweet(tweet: ReviewDTO)



}