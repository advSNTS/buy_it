package com.example.buy_it.data

import java.time.LocalDate

data class ReviewInfo(
    val id: String,
    val userId: String,
    val profileImage: String,
    val imgProd: String,
    val name: String,
    val review: String,
    val productId: String,
    val product: String,
    val like: Boolean,
    val percentageLikes: Int,
    val date: LocalDate,
    val range: String,
    val comments: Int
)