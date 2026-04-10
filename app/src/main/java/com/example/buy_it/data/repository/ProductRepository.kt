package com.example.buy_it.data.repository

import coil.network.HttpException
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.datasource.ProductRemoteDataSource
import com.example.buy_it.data.datasource.impl.ProductRetrofitDatasourceImpl
import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.toProductInfo
import com.example.buy_it.data.dtos.toReviewInfo
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductRetrofitDatasourceImpl
){
    suspend fun getAllProducts(): Result<List<ProductInfo>> {
        return try {
            val products = productRemoteDataSource.getAllProducts()
            val productsInfo = products.map { dto ->
                val reviews = try {
                    productRemoteDataSource.getProductReviews(dto.id)
                } catch (e: Exception) {
                    emptyList()
                }
                dto.toProductInfo().copy(ratingsCount = reviews.size)
            }
            Result.success(productsInfo)
        }catch (e: HttpException){
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createProduct(name: String, brand: String, imageUrl: String?, description: String?, range: String?): Result<Unit> {
        return try {
            val createProductDTO = CreateProductDTO(name, brand, imageUrl, description, range)
            productRemoteDataSource.createProduct(createProductDTO)
            Result.success(Unit)
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: String): Result<ProductInfo> {
        return try {
            val productDTO = productRemoteDataSource.getProduct(id)
            val reviews = try {
                productRemoteDataSource.getProductReviews(id)
            } catch (e: Exception) {
                emptyList()
            }
            Result.success(productDTO.toProductInfo().copy(ratingsCount = reviews.size))
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductReviews(id: String): Result<List<com.example.buy_it.data.ReviewInfo>> {
        return try {
            val reviews = productRemoteDataSource.getProductReviews(id)
            Result.success(reviews.map { it.toReviewInfo() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}