package com.example.buy_it.data

import androidx.annotation.DrawableRes
import java.util.Date

data class CommentItem(
    @DrawableRes val pfp: Int,
    val name: String,
    val username: String,
    val comment: String,
    val time: Int,
    val likes: Int,
)
