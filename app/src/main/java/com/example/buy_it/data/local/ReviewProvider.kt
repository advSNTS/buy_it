package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate
import java.util.Date

object ReviewProvider {
    val feed = listOf(
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.rey,
            name = "buy it.",
            review = "Buen jabón, con él me hago los rituales de purificación.",
            productId = "rey_300g",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 25
        ),
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.cafe,
            name = "@buy it.",
            review = "Es feo, pero barato. Además me rinde para unas 8 tazas.",
            productId = "cafe_110g",
            product = "Café TOSTA’O 110g",
            like = false,
            percentageLikes = 60,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 33
        ),
        ReviewInfo(
            pfp = R.drawable.predet,
            imgProd = R.drawable.rey,
            name = "@Danny_Slicer",
            review = "Como es eso de que el dueño de la app se purifica con esto jajaja, en fin. Buen jabón.",
            productId = "rey_300g",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 25
        )
    )
}