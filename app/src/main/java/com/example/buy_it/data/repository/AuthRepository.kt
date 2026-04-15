package com.example.buy_it.data.repository

import com.example.buy_it.data.datasource.AuthRemoteDataSource
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepository @Inject constructor (
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    val currentUser: FirebaseUser?
    get() = authRemoteDataSource.currentUser

    suspend fun signIn(email: String, password: String): Result<Unit>{
        try {
            authRemoteDataSource.signInWithEmailAndPassword(email, password)
            return Result.success(Unit)
        }
        catch (e: FirebaseAuthInvalidUserException) {
            return Result.failure(Exception("El usuario no es válido"))
        }
        catch (e: FirebaseAuthInvalidCredentialsException){
            return Result.failure(Exception("El email o la contraseña es incorrecto"))
        }
        catch (e: FirebaseNetworkException) {
            return Result.failure(Exception("Sin conexión a internet"))
        }
        catch (e: Exception) {
            return Result.failure(Exception("Error inesperado: ${e.localizedMessage}"))
        }
    }

    suspend fun signUp(email: String, password: String): Result<Unit>{
        try {
            authRemoteDataSource.signUpWithEmailAndPassword(email, password)
            return Result.success(Unit)
        }
        catch (e: FirebaseAuthUserCollisionException) {
            return Result.failure(Exception("Este correo ya está registrado"))
        }
        catch (e: FirebaseAuthWeakPasswordException) {
            return Result.failure(Exception("La contraseña es muy débil: ${e.reason}"))
        }
        catch (e: FirebaseAuthInvalidCredentialsException) {
            return Result.failure(Exception("El formato del correo no es válido"))
        }
        catch (e: FirebaseNetworkException) {
            return Result.failure(Exception("Sin conexión a internet"))
        }
        catch (e: Exception) {
            return Result.failure(Exception("Error inesperado al crear la cuenta: ${e.localizedMessage}"))
        }
    }

    fun signOut(){
        authRemoteDataSource.signOut()
    }



}