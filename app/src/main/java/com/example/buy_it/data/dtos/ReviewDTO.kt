package com.example.buy_it.data.dtos

import com.example.buy_it.data.ReviewInfo
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

data class ReviewDTO(
    val id: String = "",
    val userId: String = "",
    val productId: String = "",
    val like: Boolean = false,
    val comment: String = "",
    val comments: Int = 0,
    val createdAt: String = "",
    val user: UserDTO? = null,
    val product: ProductDTO? = null
) {
    constructor() : this("", "", "", true, "", 0, "", null, null)
}

fun ReviewDTO.toReviewInfo(): ReviewInfo {

    return ReviewInfo(
        id = id,
        userId = userId,
        profileImage = user?.pfpURL ?: "",
        imgProd = product?.imageURL ?: "",
        name = user?.name ?: "Usuario desconocido",
        review = comment,
        productId = productId,
        product = product?.name ?: "",
        like = like,
        percentageLikes = product?.percentageLike ?: 0,
        date = LocalDate.now(),
        range = product?.range ?: "",
        comments = comments
    )
}