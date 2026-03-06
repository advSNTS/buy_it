package com.example.buy_it.data

import androidx.annotation.DrawableRes

data class PricedItems(
    val name: String,
    val price: Double,
    @DrawableRes val image: Int,
    val percentage: Int,
)
