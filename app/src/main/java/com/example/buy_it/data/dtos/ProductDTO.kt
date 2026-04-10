package com.example.buy_it.data.dtos

import com.example.buy_it.data.ProductInfo

data class ProductDTO(
    val id: String,
    val name: String,
    val brand: String,
    val imageURL: String?,
    val description: String?,
    val percentageLike: Int,
    val range: String,
    val created: String
)

fun ProductDTO.toProductInfo(): ProductInfo {
    return ProductInfo(
        id = id,
        name = name,
        image = imageURL ?: "",
        description = description ?: "",
        likePercent = percentageLike,
        range = range,
        ratingsCount = 0
    )
}
