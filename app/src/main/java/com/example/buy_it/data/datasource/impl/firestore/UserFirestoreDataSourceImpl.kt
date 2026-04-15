package com.example.buy_it.data.datasource.impl.firestore

import com.example.buy_it.data.datasource.UserRemoteDatasource
import com.example.buy_it.data.datasource.impl.UserRetrofitDatasourceImplementation
import com.example.buy_it.data.dtos.RegisterUserDto
import com.example.buy_it.data.dtos.ReviewDTO
import com.example.buy_it.data.dtos.UserDtoGeneric
import com.example.buy_it.data.dtos.UserProfileFirestoreDTO
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserFirestoreDataSourceImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val userRetrofitDatasource: UserRetrofitDatasourceImplementation
): UserRemoteDatasource {

    override suspend fun getUserById(id: String): UserProfileFirestoreDTO {

        val docRef = db.collection("users").document(id)
        val respuesta = docRef.get().await()

        return respuesta.toObject(UserProfileFirestoreDTO::class.java) ?: throw Exception("Usuario no encontrado.")
    }

    override suspend fun getUserReviews(id: String): List<ReviewDTO> {
        return userRetrofitDatasource.getUserReviews(id)
    }

    override suspend fun registerUser(registerUserDto: RegisterUserDto, userId: String) {
        val docRef = db.collection("users").document(userId)
        docRef.set(registerUserDto).await()
    }
}