package com.example.buy_it.data.datasource.impl

import com.example.buy_it.data.datasource.ProductRemoteDataSource
import com.example.buy_it.data.datasource.services.ProductRetrofitService
import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.ProductDTO
import com.example.buy_it.data.dtos.ReviewDTO
import javax.inject.Inject

class ProductRetrofitDatasourceImpl @Inject constructor(
    val service: ProductRetrofitService
): ProductRemoteDataSource {
    override suspend fun getAllProducts(): List<ProductDTO> {
        return service.getAllProducts()
    }

    override suspend fun getProduct(id: String): ProductDTO {
        return service.getProduct(id)
    }

    override suspend fun getProductReviews(id: String): List<ReviewDTO> {
        return service.getProductReviews(id)
    }

    override suspend fun createProduct(tweet: CreateProductDTO) {
        return service.createProduct(tweet)
    }

    override suspend fun deleteProduct(id: String) {
        return service.deleteProduct(id)
    }

    override suspend fun updateProduct(
        id: String,
        tweet: CreateProductDTO
    ) {
        return service.updateProduct(id, tweet)
    }
}