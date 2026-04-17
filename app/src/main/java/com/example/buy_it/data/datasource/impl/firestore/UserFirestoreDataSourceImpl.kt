package com.example.buy_it.data.datasource.impl.firestore

import android.util.Log
import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.dtos.RegisterUserDto
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserProfileFirestoreDTO
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val userRetrofitDatasource: UserRetrofitDatasourceImplementation
) : UserRemoteDatasource {

    override suspend fun getUserById(id: String): UserProfileFirestoreDTO {
        val docRef = db.collection("users").document(id)
        val respuesta = docRef.get().await()

        return respuesta.toObject(UserProfileFirestoreDTO::class.java)
            ?: throw Exception("Usuario no encontrado.")
    }

    override suspend fun getUserReviews(id: String): List<ReviewDTO> {
        return try {
            val snapshot = db.collectionGroup("reviews")
                .whereEqualTo("userId", id)
                .get()
                .await()

            snapshot.toObjects(ReviewDTO::class.java)
        } catch (e: Exception) {
            Log.e("UserReviews", "Error al obtener reseñas del usuario: ${e.message}")
            emptyList()
        }
    }

    override suspend fun registerUser(registerUserDto: RegisterUserDto, userId: String) {
        val docRef = db.collection("users").document(userId)
        docRef.set(registerUserDto).await()
    }

    override suspend fun updateUserProfile(userId: String, name: String, pfpURL: String?) {
        val updates = mutableMapOf<String, Any>(
            "name" to name
        )

        if (!pfpURL.isNullOrBlank()) {
            updates["pfpURL"] = pfpURL
        }

        db.collection("users")
            .document(userId)
            .set(updates, SetOptions.merge())
            .await()
    }
}