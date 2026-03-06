package com.example.buy_it.ui.screens.home

import com.example.buy_it.data.ReviewInfo

data class HomeState(
    val reviews: List<ReviewInfo> = emptyList(),
)