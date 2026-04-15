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
import com.example.buy_it.data.dtos.toReviewInfo
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productRemoteDataSource: ProductFirestoreDatasourceImpl,
    private val userRemoteDataSource: UserFirestoreDataSourceImpl
){
    suspend fun getAllProducts(): Result<List<ProductInfo>> {
        return try {
            val products = productRemoteDataSource.getAllProducts()
            val productsInfo = products.map { dto ->
                Log.d("getAllProds", "id pasado: ${dto.id}")
                val reviews = try {
                    productRemoteDataSource.getProductReviews(dto.id)
                } catch (e: Exception) {
                    Log.d("ratings", "no se encontro review by prod")
                    Log.e("ratings", "Error real: ${e.message}")
                    Log.e("ratings", "Stacktrace: ${e.printStackTrace()}")
                    emptyList()

                }
                Log.d("ratings", "ratings count: ${reviews.size}")
                dto.toProductInfo().copy(ratingsCount = reviews.size)
            }
            Log.d("prods", "Productos: $productsInfo")
            Result.success(productsInfo)
        }catch (e: HttpException){
            Log.d("prods", "ERROR http: $e")
            Result.failure(e)
        } catch (e: Exception) {
            Log.d("prods", "ERROR: $e")
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

    suspend fun getProductReviews(id: String): Result<List<ReviewInfo>> {
        return try {
            val reviewDTOs = productRemoteDataSource.getProductReviews(id)
            val userCache = mutableMapOf<String, com.example.buy_it.data.UserProfileInfo>()

            val reviewInfos = reviewDTOs.map { dto ->
                var info = dto.toReviewInfo()
                
                // Si el nombre es "Usuario desconocido", intentamos cargarlo manualmente por su userId
                if (info.name == "Usuario desconocido") {
                    val userInfo = userCache.getOrPut(dto.userId) {
                        userRemoteDataSource.getUserById(dto.userId).toUserProfileInfo()
                    }
                    info = info.copy(
                        name = userInfo.name,
                        profileImage = userInfo.pfpURL
                    )
                }
                info
            }
            Result.success(reviewInfos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
