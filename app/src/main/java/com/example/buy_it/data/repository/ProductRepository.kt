package com.example.buy_it.data.repository

import android.util.Log
import coil.network.HttpException
import com.example.buy_it.data.ProductInfo
import com.example.buy_it.data.ReviewInfo
import com.example.buy_it.data.datasource.impl.ProductRetrofitDatasourceImpl
import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.datasource.impl.firestore.ProductFirestoreDatasourceImpl
import com.example.buy_it.data.datasource.impl.firestore.UserFirestoreDataSourceImpl
import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.toProductInfo
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductRetrofitDatasourceImpl,
    private val userRemoteDataSource: UserRetrofitDatasourceImplementation,
    private val reviewRepository: ReviewRepository
){
    suspend fun getAllProducts(): Result<List<ProductInfo>> {
        return try {
            val products = productRemoteDataSource.getAllProducts()
            val productsInfo = products.map { dto ->
                val reviews = reviewRepository.getReviewsByProductId(dto.id).getOrDefault(emptyList())
                dto.toProductInfo().copy(ratingsCount = reviews.size)
            }
            Log.d("prods", "Productos: $productsInfo")
            Result.success(productsInfo)
        } catch (e: HttpException){
            Log.d("prods", "ERROR http: $e")
            Result.failure(e)
        } catch (e: Exception) {
            Log.d("prods", "ERROR: $e")
            Result.failure(e)
        }
    }

    suspend fun createProduct(
        name: String,
        brand: String,
        imageUrl: String?,
        description: String?,
        range: String?
    ): Result<Unit> {
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
            val reviews = reviewRepository.getReviewsByProductId(id).getOrDefault(emptyList())
            Result.success(productDTO.toProductInfo().copy(ratingsCount = reviews.size))
        } catch (e: HttpException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductReviews(id: String): Result<List<ReviewInfo>> {
        return try {
            val reviewInfos = reviewRepository.getReviewsByProductId(id).getOrDefault(emptyList())

            val completedReviews = reviewInfos.map { info ->
                if (info.name == "Usuario desconocido") {
                    try {
                        val userInfo = userRemoteDataSource.getUserById(info.userId).toUserProfileInfo()
                        info.copy(
                            name = userInfo.name,
                            profileImage = userInfo.pfpURL
                        )
                    } catch (e: Exception) {
                        info
                    }
                } else {
                    info
                }
            }

            Result.success(completedReviews)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}