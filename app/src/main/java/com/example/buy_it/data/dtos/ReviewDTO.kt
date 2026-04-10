package com.example.buy_it.data.dtos

import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate
import android.util.Log

data class ReviewDTO(
    val id: String,
    val userId: String,
    val productId: String,
    val like: Boolean,
    val comment: String,
    val comments: Int,
    val createdAt: String,
    val user: UserDTO?,
    val product: ProductDTO?
)

fun ReviewDTO.toReviewInfo(): ReviewInfo {
    val parsedDate = try {
        if (createdAt.contains("T")) {
            LocalDate.parse(createdAt.substring(0, 10))
        } else {
            LocalDate.parse(createdAt)
        }
    } catch (e: Exception) {
        Log.e("ReviewDTO", "Error parseando fecha: $createdAt", e)
        LocalDate.now()
    }

    return ReviewInfo(
        id = id,
        profileImage = user?.pfpURL ?: "",
        imgProd = product?.imageURL ?: "",
        name = user?.name ?: "Usuario desconocido",
        review = comment,
        productId = productId,
        product = product?.name ?: "",
        like = like,
        percentageLikes = product?.percentageLike ?: 0,
        date = parsedDate,
        range = product?.range ?: "",
        comments = comments
    )
}
