package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.TrendInfo

object TrendProvider {
    val trends = listOf(
        TrendInfo(id = "mr_tea_limon", name = "MR TEA limón", image = R.drawable.mrtea, ratingPercent = 98, deltaPercent = 3),
        TrendInfo(id = "rey_300g", name = "Jabón REY", image = R.drawable.rey, ratingPercent = 68, deltaPercent = -13),
        TrendInfo(id = "cafe_110g", name = "Café TOSTAO", image = R.drawable.cafe, ratingPercent = 60, deltaPercent = 3)
    )
}