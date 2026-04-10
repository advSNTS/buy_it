package com.example.buy_it.data.datasource.local

import com.example.buy_it.R
import com.example.buy_it.data.ProductInfo

object ProductProvider {
    val products = listOf(
        ProductInfo(
            id = "rey_300g",
            name = "Jabón REY 300g",
            image = "",
            range = "$5000 - $6000",
            likePercent = 89,
            ratingsCount = 30,
            description = TODO()
        ),
        ProductInfo(
            id = "cafe_110g",
            name = "Café TOSTA’O 110g",
            image = "",
            range = "$5000 - $6000",
            likePercent = 60,
            ratingsCount = 33,
            description = TODO()
        ),
        ProductInfo(
            id = "mr_tea_limon",
            name = "MR TEA limón",
            image = "",
            range = "$2500 - $3500",
            likePercent = 98,
            ratingsCount = 120,
            description = TODO()
        ),
        ProductInfo(
            id = "mogolla_ext",
            name = "Mogolla Integral",
            image = "",
            range = "$1200 - $2000",
            likePercent = 85,
            ratingsCount = 45,
            description = TODO()
        ),
        ProductInfo(
            id = "toalla_alg",
            name = "Toalla Algodón",
            image = "",
            range = "$15000 - $25000",
            likePercent = 75,
            ratingsCount = 80,
            description = TODO()
        ),
        ProductInfo(
            id = "carne_res",
            name = "Carne de Res",
            image = "",
            range = "$20000 - $35000",
            likePercent = 88,
            ratingsCount = 150,
            description = TODO()
        ),
        ProductInfo(
            id = "cafe_molido",
            name = "Café Molido",
            image = "",
            range = "$8000 - $12000",
            likePercent = 95,
            ratingsCount = 95,
            description = TODO()
        ),
        ProductInfo(
            id = "jabon_barra",
            name = "Jabón Barra Extra",
            image = "",
            range = "$3000 - $4500",
            likePercent = 70,
            ratingsCount = 25,
            description = TODO()
        ),
        ProductInfo(
            id = "mr_tea_durazno",
            name = "MR TEA Durazno",
            image = "",
            range = "$2500 - $3500",
            likePercent = 89,
            ratingsCount = 65,
            description = TODO()
        )
    )

    fun byId(id: String): ProductInfo? = products.firstOrNull { it.id == id }
}
