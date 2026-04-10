package com.example.buy_it.data.datasource.services

import com.example.buy_it.data.dtos.CreateReviewDTO
import com.example.buy_it.data.dtos.ReviewDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import com.example.buy_it.data.dtos.ReviewDTO
import retrofit2.http.GET
import retrofit2.http.POST


interface ReviewRetrofitService {

    @GET("reviews")
    suspend fun getAllTweets(): List<ReviewDTO>

    @POST("reviews")
    suspend fun postTweet(tweet: ReviewDTO)



}