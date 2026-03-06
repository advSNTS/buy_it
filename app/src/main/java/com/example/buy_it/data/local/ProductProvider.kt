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
        ),
        ProductInfo(
            id = "mr_tea_limon",
            name = "MR TEA limón",
            image = R.drawable.mrtea,
            range = "$2500 - $3500",
            likePercent = 98,
            ratingsCount = 120
        ),
        ProductInfo(
            id = "mogolla_ext",
            name = "Mogolla Integral",
            image = R.drawable.mogolla,
            range = "$1200 - $2000",
            likePercent = 85,
            ratingsCount = 45
        ),
        ProductInfo(
            id = "toalla_alg",
            name = "Toalla Algodón",
            image = R.drawable.toalla,
            range = "$15000 - $25000",
            likePercent = 75,
            ratingsCount = 80
        ),
        ProductInfo(
            id = "carne_res",
            name = "Carne de Res",
            image = R.drawable.carne,
            range = "$20000 - $35000",
            likePercent = 88,
            ratingsCount = 150
        ),
        ProductInfo(
            id = "cafe_molido",
            name = "Café Molido",
            image = R.drawable.cafe,
            range = "$8000 - $12000",
            likePercent = 95,
            ratingsCount = 95
        ),
        ProductInfo(
            id = "jabon_barra",
            name = "Jabón Barra Extra",
            image = R.drawable.rey,
            range = "$3000 - $4500",
            likePercent = 70,
            ratingsCount = 25
        ),
        ProductInfo(
            id = "mr_tea_durazno",
            name = "MR TEA Durazno",
            image = R.drawable.mrtea,
            range = "$2500 - $3500",
            likePercent = 89,
            ratingsCount = 65
        )
    )

    fun byId(id: String): ProductInfo? = products.firstOrNull { it.id == id }
}
