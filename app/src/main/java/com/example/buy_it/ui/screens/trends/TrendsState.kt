package com.example.buy_it.ui.screens.trends

import com.example.buy_it.data.TrendInfo

data class TrendsState(
    val searchQuery: String = "",
    val trends: List<TrendInfo> = emptyList(),
    val filteredTrends: List<TrendInfo> = emptyList()
)