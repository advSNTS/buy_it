package com.example.buy_it.data.dtos

import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate

data class ReviewDTO(
    val id: String,
    val userID: String,
    val productId: String,val like: Boolean,
    val comment: String,
    val comments: Int,
    val createdAt: String,
    val user: UserDTO,
    val product: ProductDTO
)

fun ReviewDTO.toReviewInfo(): ReviewInfo {
    return ReviewInfo(
        id = id,
        profileImage = user.pfpurl ?: "",
        imgProd = 0,
        name = user.name,
        review = comment,
        productId = productId,
        product = product.name,
        like = like,
        percentageLikes = product.percentageLike,
        date = try {
            LocalDate.parse(createdAt.substring(0, 10))
        } catch (e: Exception) {
            LocalDate.now()
        },
        range = product.range,
        comments = comments
    )
}
