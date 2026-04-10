package com.example.buy_it.data.dtos

data class CreateReviewDTO(
    val userID: String,
    val productId: String,
    val like: Boolean,
    val comment: String
)
