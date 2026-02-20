package com.example.buy_it.data

import java.util.Date

data class ReviewInfo(
    val imageId: Int,
    val name: String,
    val username: String,
    val review: String,
    val product: String,
    val like: Boolean,
    val percentageLikes: Int,
    val date: Date,
    val range: String,
    val comments: Int
)
