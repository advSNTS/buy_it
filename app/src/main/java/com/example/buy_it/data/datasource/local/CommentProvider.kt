package com.example.buy_it.data.datasource.local

import com.example.buy_it.R
import com.example.buy_it.data.CommentInfo

object CommentProvider {
    val comments = listOf(
        CommentInfo(
            id = "c1",
            productId = "rey_300g",
            username = "@juanperez",
            profileImage = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqS1-EhtFMw2cHi_ub7A_YP90Z6eaa1egQ0A&s",
            text = "Muy buen producto, lo uso todos los días",
            timeAgo = "Hace 2 horas",
            likes = 3
        ),
        CommentInfo(
            id = "c2",
            productId = "rey_300g",
            username = "@maria_dev",
            profileImage = "https://img.fcbayern.com/image/upload/f_auto/q_auto/t_cms-1x1-seo/v1753859302/cms/public/images/fcbayern-com/players/spielerportraits/teaser/luis-diaz.png",
            text = "No me gustó el aroma",
            timeAgo = "Hace 1 día",
            likes = 1
        )
    )

    fun byProduct(productId: String): List<CommentInfo> =
        comments.filter { it.productId == productId }
}