package com.example.buy_it.data.datasource.local

import com.example.buy_it.R
import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate
import java.util.Date

object ReviewProvider {
    val feed = listOf(
        ReviewInfo(
            profileImage = "https://store.networkchuck.com/cdn/shop/files/NetworkChuck-logo_new_155091cc-4294-4449-859d-92f5c27921d0.png?v=1595374929",
            imgProd = "",
            name = "buy it.",
            review = "Buen jabón, con él me hago los rituales de purificación.",
            productId = "rey_300g",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 25,
            id = TODO()
        ),
        ReviewInfo(
            profileImage = "https://img.fcbayern.com/image/upload/f_auto/q_auto/t_cms-1x1-seo/v1753859302/cms/public/images/fcbayern-com/players/spielerportraits/teaser/luis-diaz.png",
            imgProd = "",
            name = "@buy it.",
            review = "Es feo, pero barato. Además me rinde para unas 8 tazas.",
            productId = "cafe_110g",
            product = "Café TOSTA’O 110g",
            like = false,
            percentageLikes = 60,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 33,
            id = TODO()
        ),
        ReviewInfo(
            profileImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqS1-EhtFMw2cHi_ub7A_YP90Z6eaa1egQ0A&s",
            imgProd = "",
            name = "@Danny_Slicer",
            review = "Como es eso de que el dueño de la app se purifica con esto jajaja, en fin. Buen jabón.",
            productId = "rey_300g",
            product = "Jabón REY 300g",
            like = false,
            percentageLikes = 89,
            date = LocalDate.now(),
            range = "$5000 - $6000",
            comments = 25,
            id = TODO()
        )
    )
}