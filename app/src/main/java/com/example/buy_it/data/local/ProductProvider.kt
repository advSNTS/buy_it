package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.ProductInfo

object ProductProvider {
    val products = listOf(
        ProductInfo(
            id = "rey_300g",
            name = "Jabón REY 300g",
            image = R.drawable.rey,
            range = "$5000 - $6000",
            likePercent = 89,
            ratingsCount = 30
        ),
        ProductInfo(
            id = "cafe_110g",
            name = "Café TOSTA’O 110g",
            image = R.drawable.cafe,
            range = "$5000 - $6000",
            likePercent = 60,
            ratingsCount = 33
        )
    )

    fun byId(id: String): ProductInfo? = products.firstOrNull { it.id == id }
}