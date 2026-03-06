package com.example.buy_it.data

import androidx.annotation.DrawableRes

data class TrendInfo(
    val id: String,
    val name: String,
    @DrawableRes val image: Int,
    val ratingPercent: Int,
    val deltaPercent: Int
)