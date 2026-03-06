package com.example.buy_it.data.local

import com.example.buy_it.R
import com.example.buy_it.data.CommentInfo

object CommentProvider {
    val comments = listOf(
        CommentInfo(
            id = "c1",
            productId = "rey_300g",
            username = "@juanperez",
            avatar = R.drawable.predet,
            text = "Muy buen producto, lo uso todos los días",
            timeAgo = "Hace 2 horas",
            likes = 3
        ),
        CommentInfo(
            id = "c2",
            productId = "rey_300g",
            username = "@maria_dev",
            avatar = R.drawable.predet,
            text = "No me gustó el aroma",
            timeAgo = "Hace 1 día",
            likes = 1
        )
    )

    fun byProduct(productId: String): List<CommentInfo> =
        comments.filter { it.productId == productId }
}