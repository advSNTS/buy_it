package com.example.buy_it.ui.screens.prices

import com.example.buy_it.data.PricedItems

data class PricesState(
    val pricedItems: List<PricedItems> = emptyList(),
    val isLoading: Boolean = false
)