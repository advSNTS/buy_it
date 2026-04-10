package com.example.buy_it.data.dtos

import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.ReviewInfo
import java.time.LocalDate

data class ProductDTO(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val percentageLikes: Int,
    val range: String
)

fun ProductDTO.toProductInfo(): ProductInfo {
    return ProductInfo(
        id = id.toString(),
        name = name,
        image = 0,
        range = range,
        likePercent = percentageLikes,
        ratingsCount = 0
    )
}