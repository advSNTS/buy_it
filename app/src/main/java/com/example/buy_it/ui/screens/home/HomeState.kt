package com.example.buy_it.ui.screens.home

import com.example.buy_it.data.ProductInfo

data class HomeState(
    val products: List<ProductInfo> = emptyList(),
    val allProducts: List<ProductInfo> = emptyList(),
    val isFollowingFilterActive: Boolean = false
)