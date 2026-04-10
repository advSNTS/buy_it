package com.example.buy_it.data.repository

import coil.network.HttpException
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.datasource.ProductRemoteDataSource
import com.example.buy_it.data.datasource.impl.ProductRetrofitDatasourceImpl
import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.toProductInfo
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


}