package com.example.buy_it.data

import androidx.annotation.DrawableRes

data class CommentInfo(
    val id: String,
    val productId: String,
    val username: String,
    @DrawableRes val avatar: Int,
    val text: String,
    val timeAgo: String,
    val likes: Int
)