package com.example.buy_it.data

import androidx.annotation.DrawableRes

data class ProductInfo(
    val id: String,
    val name: String,
    @DrawableRes val image: Int,
    val range: String,
    val likePercent: Int,
    val ratingsCount: Int
)