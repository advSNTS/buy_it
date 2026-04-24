package com.example.buy_it.ui.screens.home

import com.example.buy_it.data.ReviewInfo

data class HomeState(
    val reviews: List<ReviewInfo> = emptyList(),
    val filteredReviews: List<ReviewInfo> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false
)