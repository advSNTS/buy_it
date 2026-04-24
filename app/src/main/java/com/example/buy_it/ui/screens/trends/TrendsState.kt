package com.example.buy_it.ui.screens.trends

import com.example.buy_it.data.ReviewInfo

data class TrendsState(
    val searchQuery: String = "",
    val reviews: List<ReviewInfo> = emptyList(),
    val filteredReviews: List<ReviewInfo> = emptyList(),
    val isLoading: Boolean = false
)