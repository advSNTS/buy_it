package com.example.buy_it.data.datasource.impl.firestore

import com.example.buy_it.data.datasource.ProductRemoteDataSource
import com.example.buy_it.data.dtos.CreateProductDTO
import com.example.buy_it.data.dtos.ProductDTO
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileFirestoreDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProductFirestoreDatasourceImpl @Inject constructor(
    private val db: FirebaseFirestore
): ProductRemoteDataSource {
    override suspend fun getAllProducts(): List<ProductDTO> {
        val snapshot = db.collection("products").get().await()

        return snapshot.documents.map { document ->
            val dto = document.toObject(ProductDTO::class.java) ?: ProductDTO()
            dto.copy(id = document.id)
        }
    }

    override suspend fun getProduct(id: String): ProductDTO {
        val docRef = db.collection("products").document(id)
        val respuesta = docRef.get().await()

        return respuesta.toObject(ProductDTO::class.java) ?: throw Exception("Producto no Encontrado.")
    }

    override suspend fun getProductReviews(id: String): List<ReviewDTO> {
        return try {
            val snapshot = db.collection("products")
                .document(id)
                .collection("reviews")
                .get()
                .await()
            snapshot.toObjects(ReviewDTO::class.java)
        } catch (e: Exception) {
            throw Exception("Error al obtener reseñas: ${e.message}")
        }
    }

    override suspend fun createProduct(tweet: CreateProductDTO) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateProduct(
        id: String,
        tweet: CreateProductDTO
    ) {
        TODO("Not yet implemented")
    }
}