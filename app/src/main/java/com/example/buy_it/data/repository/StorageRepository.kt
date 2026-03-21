package com.example.buy_it.data.repository

import android.net.Uri
import android.util.Log
import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.example.buy_it.data.datasource.StorageRemoteDataSource
import com.google.firebase.FirebaseNetworkException
import javax.inject.Inject

class StorageRepository @Inject constructor(
    private val storage: StorageRemoteDataSource,
    private val auth: AuthRemoteDataSource
){
    suspend fun uploadProfileImage(uri: Uri): Result<String> {
        return try {
            val userId = auth.currentUser?.uid ?: return Result.failure(Exception("No hay usuario logueado"))
            val path = "profilePictures/$userId.jpg"
            val imageUrl = storage.uploadImage(path, uri)


            //Actualizar url del usuario
            auth.updateProfileImage(imageUrl)

            Result.success(imageUrl)
        } catch (e: FirebaseNetworkException) {
            Log.e("DEBUGGG", "Error de red: ${e.localizedMessage}")
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: Exception) {
            Log.e("DEBUGGG", "Error al subir la imagen: ${e.localizedMessage}")
            Result.failure(Exception("Error al subir la imagen: ${e.localizedMessage}"))
        }
    }
}