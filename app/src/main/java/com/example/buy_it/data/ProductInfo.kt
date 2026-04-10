package com.example.buy_it.data

import androidx.annotation.DrawableRes

data class ProductInfo(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val likePercent: Int,
    val range: String,
    val ratingsCount: Int
)