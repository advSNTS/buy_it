package com.example.buy_it.data.dtos

import com.example.buy_it.data.ReviewInfo
import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class UserDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("pfpURL") val pfpurl: String? = null
)

data class ProductDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String? = null,
    @SerializedName("imageURL") val imageUrl: String? = null,
    @SerializedName("percentageLike") val percentageLikes: Int? = null,
    @SerializedName("range") val range: String? = null
)

data class ReviewDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("userId") val userID: Int,
    @SerializedName("productId") val productId: Int,
    @SerializedName("like") val like: Boolean,
    @SerializedName("comment") val comment: String,
    @SerializedName("comments") val comments: Int? = null,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("user") val user: UserDTO? = null,
    @SerializedName("product") val product: ProductDTO? = null
)

data class CreateReviewDTO(
    @SerializedName("userId") val userId: Int,
    @SerializedName("productId") val productId: Int,
    @SerializedName("like") val like: Boolean,
    @SerializedName("comment") val comment: String
)

fun ReviewDTO.toReviewInfo(): ReviewInfo {
    return ReviewInfo(
        id = id.toString(),
        profileImage = user?.pfpurl ?: "",
        imgProd = 0,
        name = user?.name ?: "buy it.",
        review = comment,
        productId = productId.toString(),
        product = product?.name ?: "Producto",
        like = like,
        percentageLikes = product?.percentageLikes ?: 0,
        date = LocalDate.parse(createdAt.substring(0, 10)),
        range = product?.range ?: "",
        comments = comments ?: 0
    )
}