package com.example.buy_it.data.dtos

data class CreateReviewDTO(
    val userId: String,
    val productId: String,
    val like: Boolean,
    val comment: String
)