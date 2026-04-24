package com.example.buy_it.ui.screens.trends

import com.example.buy_it.data.ProductInfo

data class TrendsState(
    val searchQuery: String = "",
    val products: List<ProductInfo> = emptyList(),
    val filteredProducts: List<ProductInfo> = emptyList(),
    val isLoading: Boolean = false
)