package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.TrendInfo


object TrendProvider {
    val trends = listOf(
        TrendInfo(id = "mr_tea_limon", name = "MR TEA limón", image = R.drawable.mrtea, ratingPercent = 98, deltaPercent = 3),
        TrendInfo(id = "rey_300g", name = "Jabón REY", image = R.drawable.rey, ratingPercent = 68, deltaPercent = -13),
        TrendInfo(id = "cafe_110g", name = "Café TOSTAO", image = R.drawable.cafe, ratingPercent = 60, deltaPercent = 3),
        TrendInfo(id = "mogolla_ext", name = "Mogolla Integral", image = R.drawable.mogolla, ratingPercent = 85, deltaPercent = 12),
        TrendInfo(id = "toalla_alg", name = "Toalla Algodón", image = R.drawable.toalla, ratingPercent = 75, deltaPercent = -5),
        TrendInfo(id = "carne_res", name = "Carne de Res", image = R.drawable.carne, ratingPercent = 88, deltaPercent = 7),
        TrendInfo(id = "cafe_molido", name = "Café Molido", image = R.drawable.cafe, ratingPercent = 95, deltaPercent = 10),
        TrendInfo(id = "jabon_barra", name = "Jabón Barra Extra", image = R.drawable.rey, ratingPercent = 70, deltaPercent = -2),
        TrendInfo(id = "mr_tea_durazno", name = "MR TEA Durazno", image = R.drawable.mrtea, ratingPercent = 89, deltaPercent = 15)
    )
}