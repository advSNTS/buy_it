package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.PricedItems

object PricedItemsProvider {

    val pricedItems = listOf(
        PricedItems(
            name = "Olimpica",
            price = 100.0,
            image = R.drawable.olimpica,
            percentage = 10
        ),
        PricedItems(
            name = "Exito",
            price = 100.0,
            image = R.drawable.exito,
            percentage = 10
        ),
        PricedItems(
            name = "Carulla",
            price = 100.0,
            image = R.drawable.carulla,
            percentage = 10
        ),
        PricedItems(
            name = "Ara",
            price = 100.0,
            image = R.drawable.ara,
            percentage = 10
        ),
        PricedItems(
            name = "D1",
            price = 100.0,
            image = R.drawable.duno,
            percentage = 10
        )
    )
}