package com.example.buy_it.data

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class ReviewInfo(
    @DrawableRes val pfp: Int,
    @DrawableRes val imgProd: Int,
    val name: String,
    // val username: String,
    val review: String,
    val product: String,
    val like: Boolean,
    val percentageLikes: Int,
    val date: LocalDate,
    val range: String,
    val comments: Int
)
