package com.example.buy_it.data.datasource

import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.ProductDTO
import com.example.buy_it.data.dtos.ReviewDTO

interface ProductRemoteDataSource {

    suspend fun getAllProducts(): List<ProductDTO>
    suspend fun getProduct(id: String): ProductDTO
    suspend fun getProductReviews(id: String): List<ReviewDTO>
    suspend fun createProduct(tweet: CreateProductDTO): Unit
    suspend fun deleteProduct(id: String): Unit
    suspend fun updateProduct(id: String, tweet: CreateProductDTO): Unit

}