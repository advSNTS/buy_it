package com.example.buy_it.ui.screens.detail

import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.ReviewInfo

data class DetailState(
    val product: ProductInfo? = null,
    val reviews: List<ReviewInfo> = emptyList(),
    val isLoading: Boolean = false,
    val navigateToProfileUserId: String? = null,
    val currentUserId: String? = null
)