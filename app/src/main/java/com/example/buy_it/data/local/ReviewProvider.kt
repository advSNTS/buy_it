package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.ReviewInfo
import java.util.Date

object ReviewProvider {
    val feed = listOf(
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.rey,
            name = "buy it.",
            review = "Buen jabón, con él me hago los rituales de purificación.",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = Date(),
            range = "$5000 - $6000",
            comments = 25
        ),
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.cafe,
            name = "@buy it.",
            review = "Es feo, pero barato. Además me rinde para unas 8 tazas.",
            product = "Café TOSTA’O 110g",
            like = false,
            percentageLikes = 60,
            date = Date(),
            range = "$5000 - $6000",
            comments = 33
        ),
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.rey,
            name = "@Danny_Slicer",
            review = "Como es eso de que el dueño de la app se purifica con esto jajaja, en fin. Buen jabón.",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = Date(),
            range = "$5000 - $6000",
            comments = 25
        )
    )
}