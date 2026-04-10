package com.example.buy_it.data.dtos

import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate



data class ReviewDTO(
    val id: Int,
    val userID: Int,
    val productId: Int,
    val like: Boolean,
    val comment: String,
    val comments: Int,
    val createdAt: String,
    val user: UserDTO,
    val product: ProductDTO
)

fun ReviewDTO.toReviewInfo(): ReviewInfo {
    return ReviewInfo(
        id = id.toString(),
        profileImage = user.pfpurl ?: "",
        imgProd = 0,    //ojo que es drawable res hay que cambiarlo
        name = user.name,
        review = comment,
        productId = productId.toString(),
        product = product.name,
        like = like,
        percentageLikes = product.percentageLikes,
        date = LocalDate.parse(createdAt.substring(0, 10)), // "2026-04-09T..." → "2026-04-09"
        range = product.range,
        comments = comments
    )
}
