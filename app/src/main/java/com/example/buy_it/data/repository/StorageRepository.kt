package com.example.buy_it.data.repository

import android.net.Uri
import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.example.buy_it.data.datasource.StorageRemoteDataSource
import javax.inject.Inject

class StorageRepository @Inject constructor(
    private val storage: StorageRemoteDataSource,
    private val auth: AuthRemoteDataSource
){
    suspend fun uploadProfileImage(uri: Uri): Result<String> {
        return try {
            val userId = auth.currentUser?.uid ?: return Result.failure(Exception("No hay usuario logueado"))
            val path = "profilePictures/$userId.jpg"

            val url = storage.uploadImage(path, uri)
            val imageUrl = storage.uploadImage(path, uri)


            //Actualizar url del usuario
            auth.updateProfileImage(imageUrl)

            Result.success(imageUrl)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}